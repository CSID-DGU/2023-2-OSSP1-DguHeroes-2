import requests
import json

NAME = 'proysm/Healody_Server'
# NAME='CSID-DGU/2023-2-OSSP1-DguHeroes-2'

MAX_PER_PAGE = 100  # 용량을 줄이기 위해 테스톨 30, 실제로는 100

GH_API = 'https://api.github.com'

headers = {
    'Authorization': 'token %s' % (GITHUB_API_TOKEN),
}

GRAPH_STACK_TREE = {
    'javascript': ['express', 'socket.io', 'axios', 'react', 'angularjs', 'react native', 'electron', 'vue.js', \
                   'jquery', 'next.js', 'svelte'], 'html': ['electron'], 'css': [],
    'python': ['flask', 'django', 'pandas', 'numpy', \
               'tensorflow', 'scikit-learn', 'apache kafka', 'pytorch', 'opencv', 'opengl', 'keras', 'apache spark',
               'qt'], \
    'typescript': ['react', 'angular', 'react native'], 'java': ['springboot', 'Apache Kafka'], 'c#': ['blazor', \
                                                                                                       '.net',
                                                                                                       '.net framework'],
    'c++': ['cuda', 'apache kafka'], 'c': ['apache kafka', 'cuda'], 'php': ['laravel'], \
    'go': ['apache kafka'], 'rust': [], 'kotlin': [], 'ruby': ['ruby on rails'], 'lua': [], 'dart': [], 'swift': [], \
    'r': [], 'visual basic': ['.net', '.net framework'], 'node.js': ['express', 'socket.io', 'axios', 'electron'], \
    'flutter': [], '.net': ['blazor'], 'rabbitmq': []}

GRAPH_LANGUAGE = ['javascript', 'html', 'css', 'python', 'typescript', 'java', 'c#', 'c++', 'c', 'php', 'go', 'rust',
                  'kotlin' \
    , 'ruby', 'lua', 'dart', 'swift', 'r', 'visual basic']
''' #혹시 쓰일지도 모를, 대문자용 리스트
GRAPH_LANGUAGE = ['JavaScript', 'HTML', 'CSS', 'Python', 'TypeScript', 'Java', 'C#', 'C++', 'C', 'PHP', 'Go', 'Rust', 'Kotlin'\
               , 'Ruby', 'Lua', 'Dart',  'Swift', 'R', 'Visual Basic']
'''
list_language_extension = [['js'], ['html'], ['css'], ['py'], ['ts'], ['java', 'class', 'jsp'], ['cs'],
                           ['cc', 'cpp', 'h'] \
    , ['c', 'h'], ['php'], ['go'], ['rs'], ['kt'], ['rb'], ['lua'], ['dart'], ['s'], ['swift'], ['r'], ['vb']]


# Project 사용 언어 구하는 함수
def get_language():
    # https://api.github.com/repos/OWNER/REPO/languages
    GH_REPO = '%s/repos/%s/languages' % (GH_API, NAME)
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()
    list_language = list(response.keys())
    for language in list_language:
        if language not in GRAPH_LANGUAGE:
            list_language.remove(language)
    return list_language


# Team Members 구하는 함수 + Commit 개수 구하는 함수
def get_members():
    # 처음에는 collaborators를 구하려고 했으나, collaborators여도 기여하지 않은 경우 프로젝트 초기에 팀에서 나갔을 수도 있고,
    # collaborators가 아니면서 contributors기만 한 팀원도 있을 수 있다고 생각해 그냥 배제하기로 했다. 대신 contributors에서 필터링.

    #  https://api.github.com/repos/OWNER/REPO/contributors
    GH_REPO = '%s/repos/%s/contributors' % (GH_API, NAME)
    min_rate_commits = 0.05;  # 상대적-5퍼센트 이상은 돼야 팀원으로 인정
    min_cnt_commits = 10;  # 절대적-10개 이상은 돼야 팀원으로 인정
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()
    cnt_contributors = len(response)
    list_name_members = []
    list_cnt_commits = []

    for user in response:
        list_name_members.append(user['login'])
        list_cnt_commits.append(user['contributions'])

    sum_commits = sum(list_cnt_commits)
    for idx, user in enumerate(list_name_members):
        if list_cnt_commits[idx] / sum_commits < min_rate_commits or list_cnt_commits[idx] < min_cnt_commits:
            # del 이 pop보다 미세하게 빠름
            list_name_members.pop(idx)
            list_cnt_commits.pop(idx)

    return list_name_members, list_cnt_commits


# Commit Code 가져오는 함수 + 추가한 라인 수도 카운트한 뒤 합해서 전달 (깃허브 점수 2번 구하기 위해)
def get_commit_code(list_furl):
    list_addition_code = []
    list_filename_language = []
    cnt_addition = 0
    cnt_deletion = 0
    for furl in list_furl:
        response = requests.get('%s' % (furl), headers=headers)
        response = response.json()

        cnt_files = len(response['files'])
        # print(furl)
        # print(response['files'][0]['patch'])
        for idx_file in range(cnt_files):
            # status가 added, deleted, renamed 등 다양하게 있는데 이중 코드를 수정한 경우만 가져와야 하므로
            # status added를 가져오겠다.
            if response['files'][idx_file]['status'] == 'added' and response['files'][idx_file]['changes'] != 0:
                # list_addition_code.append(response['files'][idx_file]['additions'])
                filename = response['files'][idx_file]['filename'].split('.')[-1]
                list_filename_language.append(filename)
                cnt_addition += response['files'][idx_file]['additions']
                cnt_deletion += response['files'][idx_file]['deletions']
                list_addition_code.append(response['files'][idx_file]['patch'])

    return list_addition_code, list_filename_language, cnt_addition, cnt_deletion


# Commit SHA값 포함된 주소 가져오는 함수
def get_commit_sha(fauthor, fsum):
    # max_page_num = int(fsum+99/100) #실제
    max_page_num = 1  # 용량 줄이려고 테스트용
    list_url_commits = []
    for page in range(1, max_page_num + 1):
        GH_REPO = '%s/repos/%s/commits?per_page=%s&page=%s&author=%s' % (GH_API, NAME, MAX_PER_PAGE, page, fauthor)

        response = requests.get('%s' % (GH_REPO), headers=headers)
        response = response.json()

        cnt_commits = len(response)

        for idx in range(cnt_commits):
            list_url_commits.append(response[idx]['url'])

    return list_url_commits


# ---

# Main

list_name_members = []
list_cnt_commits = []
sum_commits = 0

dict_user_commit = dict()

list_language = get_language()
print(list_language)

list_name_members, list_cnt_commits = get_members()

print(list_cnt_commits)
print(list_name_members)