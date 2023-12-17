from common_variable import *

#profile에서 유저가 참여한 프로젝트 리스트 가져오는 함수

def get_profile_project_list(fname):

    USERNAME = fname

    GH_REPO = 'https://api.github.com/users/'+USERNAME+'/repos'

    response = requests.get(GH_REPO, headers=headers)
    response = response.json()

    list_repo_name = []

    if len(response) > 0:
        for repo in response:
            repo_name = repo['full_name']
            list_repo_name.append(repo_name)
    print(list_repo_name)

    while True:
        GH_REPO = '%s/search/commits?per_page=%s&page=%s&q=author:%s' % (GH_API, MAX_PER_PAGE, 1, USERNAME)

        for repo in list_repo_name:
            GH_REPO += ' -repo:' + repo

        #print(GH_REPO)

        response = requests.get('%s' % (GH_REPO), headers=headers)
        response = response.json()

        if len(response['items']) == 0:
            print("find all projects")
            break

        if len(response['items']) > 0:
            for commit in response['items']:
                new_project_name = commit['repository']['full_name']
                list_repo_name.append(new_project_name)
                break

    return list_repo_name