#깃허브 점수 4번 관심도 측정하는 함수들
#프로젝트 watch, fork, star 수 측정하는 함수:
def get_cnt_popularity():
    #https://api.github.com/repos/OWNER/REPO/subscribers
    GH_REPO='%s/repos/%s'%(GH_API, NAME)

    response = requests.get('%s'%(GH_REPO), headers=headers)
    if response.status_code == 200:
        response = response.json()

        cnt_watch = response['watchers_count']
        cnt_star = response['stargazers_coun']
        cnt_fork = response['forks_count']

        return cnt_watch
    else:
        print("error")
        return -1