import requests
import json

GITHUB_API_TOKEN = '' #Github API 시크릿 토큰
OWNER = 'proysm' #예시-추후 전체 유저를 도는 방식으로 수정해야 함
REPO = 'Healody_Server' #예시

GH_API = 'https://api.github.com'

headers = {
    'Authorization': 'token %s' % (GITHUB_API_TOKEN),
}


# Project ID 구하는 함수
def get_project_id():
    return 0


# Team Members 구하는 함수 + Commit 개수 구하는 함수
def get_members():
    # 처음에는 collaborators 를 구하려고 했으나, collaborators여도 기여하지 않은 경우 프로젝트 초기에 팀에서 나갔을 수도 있고,
    # collaborators가 아니면서 contributors기만 한 팀원도 있을 수 있다고 생각해 그냥 배제하기로 했다. 대신 contributors에서 필터링.

    #  https://api.github.com/repos/OWNER/REPO/contributors
    GH_REPO = '%s/repos/%s/%s/contributors' % (GH_API, OWNER, REPO)
    min_rate_commits = 0.05;
    min_cnt_commits = 10;
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


# Commit Code 가져오는 함수
def get_commit_code(list_furl):
    list_addition_code = []
    for furl in list_furl:
        response = requests.get('%s' % (furl), headers=headers)
        response = response.json()

        cnt_files = len(response['files'])
        # print(furl)
        # print(response['files'][0]['patch'])
        for idx_file in range(cnt_files):
            # print(response['files'][0]['additions'])
            # patch로 가져오면 코드를 가져오지만 리스트 너무 커지므로 임시로 addition 개수만 가져온다.
            list_addition_code.append(response['files'][idx_file]['additions'])

    return list_addition_code


# Commit SHA값 포함된 주소 가져오는 함수
def get_commit_sha(fsum):
    max_page_num = int(fsum + 99 / 100)
    max_page_num = 1
    list_url_commits = []
    for page in range(max_page_num):
        GH_REPO = '%s/repos/%s/%s/commits?per_page=100&page=%s' % (GH_API, OWNER, REPO, page)

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

project_id = get_project_id()
list_name_members, list_cnt_commits = get_members()
sum_commits = sum(list_cnt_commits)
list_url_commit = get_commit_sha(sum_commits)  # 데이터 구조가 이중리스트로 되어있다. 이중리스트가 빠른지, dictionary가 빠른지
# json이 빠른지 해봐야한다
list_commit_code = get_commit_code(list_url_commit)