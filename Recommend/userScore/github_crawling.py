from get_cnt_annotation import *
from get_score_popularity import *
from get_score_usability import *


# OWNER = 'proysm'
# NAME='proysm/Healody_Server'
OWNER = 'Voine'
NAME = 'Voine/ChatWaifu_Mobile'
# NAME='CSID-DGU/2023-2-OSSP1-DguHeroes-2'

MAX_PER_PAGE = 30  # 용량을 줄이기 위해 테스톨 30, 실제로는 100

GH_API ='https://api.github.com'

headers = {
    'Authorization': 'token %s ' %(GITHUB_API_TOKEN),
}

GRAPH_STACK_TREE = \
    {'javascript': ['express', 'socket.io', 'axios', 'react', 'angularjs', 'react native', 'electron', 'vue.js', \
                   'jquery', 'next.js', 'svelte'], 'html': ['electron'], 'css': [],
    'python': ['flask', 'django', 'pandas', \
               'tensorflow', 'scikit-learn', 'apache kafka', 'pytorch', 'opencv', 'opengl', 'keras', 'apache spark',
               'qt'], \
    'typescript': ['react', 'angular', 'react native'], 'java': ['springboot', 'apache kafka'], 'c#': ['blazor', \
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
                           ['cc', 'cpp', 'h', 'mm'] \
    , ['c', 'h'], ['php'], ['go'], ['rs'], ['kt'], ['rb'], ['lua'], ['dart'], ['s'], ['swift'], ['r'], ['vb']]


# Project 사용 언w어 구하는 함수
def get_language():
    # https://api.github.com/repos/OWNER/REPO/languages
    GH_REPO = '%s/repos/%s/languages' % (GH_API, NAME)
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()
    list_language = list(response.keys())
    list_language = [lang.lower() for lang in list_language]
    list_language = [lang for lang in list_language if lang in GRAPH_LANGUAGE]
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

        # print(response['files'])

        for idx_file, file in enumerate(response['files']):
            filename = file['filename'].split('.')[-1]
            list_extension = get_list_extension(list_language)
            if filename in list_extension:
                # status가 added, deleted, renamed 등 다양하게 있는데 이중 코드를 수정한 경우만 가져와야 하므로
                # status added를 가져오겠다.
                if file['status'] == 'added' and file['changes'] != 0:
                    # list_addition_code.append(file['additions'])
                    list_filename_language.append(filename)
                    cnt_addition += file['additions']
                    cnt_deletion += file['deletions']
                    print(file)
                    if 'patch' not in list(file.keys()):  # 이상하게 patch key가 response에 없는 경우 있다.
                        FILE_SHA = file['sha']
                        #  https://api.github.com/repos/OWNER/REPO/git/blobs/FILE_SHA
                        GH_REPO = '%s/repos/%s/git/blobs/%s' % (
                        GH_API, NAME, FILE_SHA)  # 그럴 경우 포함된 blob content를 받아 decode

                        response = requests.get('%s' % (GH_REPO), headers=headers)
                        response = response.json()

                        # print(response)
                        patch = base64.b64decode(response['content'])
                        # print(patch)
                        list_addition_code.append(patch)
                    else:
                        list_addition_code.append(file['patch'])

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


def get_list_extension(flist_language):  # 파일의 확장자를 구하는 함수
    list_extension = []
    for lang in flist_language:
        list_extension.extend(list_language_extension[GRAPH_LANGUAGE.index(lang)])
    # print(list_extension)
    return list_extension


def get_list_file_stack(fuser, flist_language):  # search api 사용해서 기술 스택을 추출하는 함수
    # GH_REPO='%s/search/issues?q=repo:%s/%s+type:pr'%(GH_API, OWNER, REPO)
    # GET https://api.github.com/repos/:owner/:repo/commits?path=FILE_PATH

    list_search = []  # 찾을 키워드 리스트
    list_search.extend(list_language)
    for lang in list_language:
        list_search.extend(GRAPH_STACK_TREE[lang])
    list_search = list(set(list_search))

    list_user_language = []
    list_user_stack = []
    idx_lang = 0
    print(fuser, flist_language)
    while idx_lang < len(flist_language):  # 프로젝트 사용 언어로만 반복
        print("search keyword : " + str(list_search))
        try:
            lang = flist_language[idx_lang]
            if lang not in list_search:
                idx_lang += 1
                continue
            GH_REPO = '%s/search/code?q=%s +repo:%s' % (GH_API, lang, NAME)

            response = requests.get('%s' % (GH_REPO), headers=headers)
            response = response.json()

            # print(response)
            len_file = len(response['items'])
            idx_file = 0

            while idx_file < len_file:
                try:
                    file = response['items'][idx_file]
                    file_path = file['path']

                    GH_REPO = '%s/repos/%s/commits?path=%s&author=%s' % (GH_API, NAME, file_path, fuser)

                    response = requests.get('%s' % (GH_REPO), headers=headers)
                    response = response.json()

                    # print(len(response))
                    if len(response) > 0:
                        list_user_language.append(lang)
                        if lang in list_search:
                            list_search.remove(lang)
                        break  # 특정 언어를 쓴 모든 파일을 검색해서, 관련된 커밋 중 committer가 찾는 유저인 게 있다면 for문 종료
                    idx_file += 1
                except Exception as e:
                    print("error idx_file:" + str(idx_file))
                    print(e)
                    time.sleep(5)
            print("language_list:" + str(list_user_language))
            idx_lang += 1
            if lang in list_search:
                list_search.remove(lang)
        except Exception as e:
            print("error idx_lang:" + str(idx_lang))
            print(e)
            time.sleep(5)

    print("next2")
    idx_lang = 0
    while idx_lang < len(flist_language):  # 프로젝트 사용 언어로만 반복
        print("search keyword : " + str(list_search))
        try:
            lang = flist_language[idx_lang]
            list_stack = GRAPH_STACK_TREE[lang]
            # print("사용가능한 총 스택:"+str(list_stack))
            for stack in list_stack:
                if stack not in list_search:  # 이미 유저가 쓰는 기술스택 리스트에 해당 스택이 있으면 패스.
                    continue
                GH_REPO = '%s/search/code?q=%s +repo:%s' % (GH_API, stack, NAME)

                response = requests.get('%s' % (GH_REPO), headers=headers)
                response = response.json()

                # print(response)

                len_file = len(response['items'])  # 결과가 없으면 response 자체가 3가지 key가 빈 리스토로 나온다.
                idx_file = 0

                while idx_file < len_file:
                    try:
                        file = response['items'][idx_file]
                        file_path = file['path']

                        GH_REPO = '%s/repos/%s/commits?path=%s&author=%s' % (GH_API, NAME, file_path, fuser)

                        response = requests.get('%s' % (GH_REPO), headers=headers)
                        response = response.json()

                        # print(len(response))
                        if len(response) > 0:  # 결과가 없으면 response 자체가 빈 리스트로 나온다.
                            list_user_stack.append(stack)
                            if stack in list_search:
                                list_search.remove(stack)
                            break  # 특정 프레임워크를 쓴 모든 파일을 검색해서, 관련된 커밋 중 committer가 찾는 유저인 게 있다면 for문 종료
                        idx_file += 1
                    except Exception as e:
                        print("error idx_file:" + str(idx_file))
                        print(e)
                        time.sleep(5)
                if lang in list_search:
                    list_search.remove(stack)
            print("stack_list:" + str(list_user_stack))
            idx_lang += 1
        except Exception as e:
            print("error idx_lang:" + str(idx_lang))
            print(e)
            time.sleep(5)
    list_user_stack.extend(list_user_language)
    print("final stack_list:" + str(list_user_stack))

    return list_user_stack


# 팀원 2~8인인지 체크하는 함수
def check_cnt_member():
    members = get_members()
    if len(members) >= 2 and len(members) <= 8:
        return True
    else:
        return False


def get_score_project(fdict_user, flist_language):
    sum_project_size = 0
    sum_cnt_annotation = 0
    list_user_code_size = []

    print("볼륨과 비율 구하는 중")
    for member in list_name_members:
        sum_project_size += fdict_user[member]['addition_raw']
        sum_cnt_annotation += fdict_user[member]['annotation']
        list_user_code_size.append(fdict_user[member]['addition_final'])

    list_user_code_size = [size / sum_project_size for size in list_user_code_size]
    user_code_std = np.std(np.array(list_user_code_size))

    print("관심도 구하는 중")
    popularity_watch, popularity_star, popularity_fork = get_cnt_popularity(NAME)
    print("활용도 구하는 중")
    usability_issue, usability_branch, usability_pr, usability_tag, usability_release = get_cnt_usability(NAME)
    print("주석 비율 구하는 중")
    annotation_rate = sum_cnt_annotation / sum_project_size

    dict_score_db = dict()
    dict_score_db['used_stack'] = get_list_file_stack(OWNER, flist_language)
    dict_score_db['popularity_watch'] = popularity_watch
    dict_score_db['popularity_star'] = popularity_star
    dict_score_db['popularity_fork'] = popularity_fork
    dict_score_db['usability_issue'] = usability_issue
    dict_score_db['usability_branch'] = usability_branch
    dict_score_db['usability_pr'] = usability_pr
    dict_score_db['usability_tag'] = usability_tag
    dict_score_db['usability_release'] = usability_release
    dict_score_db['commit_rate_std'] = user_code_std
    dict_score_db['project_size'] = sum_project_size
    dict_score_db['annotation_rate'] = annotation_rate

    print(dict_score_db)
    return dict_score_db


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

for idx, member in enumerate(list_name_members):
    list_url_commit = get_commit_sha(member, list_cnt_commits[idx])
    list_commit_code, list_filename, sum_addition, sum_deletion = get_commit_code(list_url_commit)
    dict_user_commit[member] = {'code': [], 'annotation': [], 'addition_raw': 0, 'addition_final': 0}
    dict_user_commit[member]['code'] = list_commit_code  # 멤버별로 커밋 코드를 포함하는 딕셔너리 생성하기
    dict_user_commit[member]['annotation'] = get_cnt_annotation(member, list_commit_code)
    dict_user_commit[member]['addition_raw'] = sum_addition  # 깃허브 점수 1번 프로젝트 용량 구할 때 이용, 유저 전체 합
    dict_user_commit[member]['addition_final'] = sum_addition - sum_deletion  # 깃허브 점수 2번, 커밋 비율 구할 때 이용

test = get_score_project(dict_user_commit, list_language)