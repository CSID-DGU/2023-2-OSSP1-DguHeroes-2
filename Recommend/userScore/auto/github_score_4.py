from common_variable import *

#깃허브 점수 4번 관심도 측정하는 함수들
#프로젝트 watch, fork, star 수 측정하는 함수:
def get_cnt_popularity(NAME):
    #https://api.github.com/repos/OWNER/REPO/subscribers
    GH_REPO='%s/repos/%s'%(GH_API, NAME)

    response = requests.get('%s'%(GH_REPO), headers=headers)
    if response.status_code == 200:
        response = response.json()
        cnt_star = response['stargazers_count']
        cnt_fork = response['forks_count']

        cnt_watch = 0
        for idx in range(1,101):
            GH_REPO='%s/repos/%s/subscribers?per_page=100&page=%s'%(GH_API, NAME, str(idx))

            response = requests.get('%s'%(GH_REPO), headers=headers)
            response = response.json()

            if len(response) == 0:
                break
            else:
                cnt_watch += len(response)

        return cnt_watch, cnt_star, cnt_fork
    else:
        print("error")
        return -1