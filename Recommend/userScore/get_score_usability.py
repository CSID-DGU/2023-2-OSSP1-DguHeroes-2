# 깃허브 점수 3번 활용량 구하는 함수들
# 프로젝트 issue 유무 측정하는 함수:
def get_cnt_issue():
    # https://api.github.com/repos/OWNER/REPO/issues
    GH_REPO = '%s/search/issues?q=repo:%s+type:issue' % (GH_API, NAME)

    response = requests.get('%s' % (GH_REPO), headers=headers)
    if response.status_code == 200:
        response = response.json()

        cnt_issue = len(response)

        return cnt_issue
    else:
        print("error")
        return -1


# 프로젝트 branch 개수 측정하는 함수:
def get_cnt_branch():
    # https://api.github.com/repos/OWNER/REPO/branches
    GH_REPO = '%s/repos/%s/branches' % (GH_API, NAME)

    response = requests.get('%s' % (GH_REPO), headers=headers)
    if response.status_code == 200:
        response = response.json()

        cnt_branch = len(response)

        return cnt_branch
    else:
        print("error")
        return -1


# 프로젝트 pr 개수 측정하는 함수:
def get_cnt_pr():
    # https://api.github.com/repos/OWNER/REPO/pull
    GH_REPO = '%s/search/issues?q=repo:%s+type:pr' % (GH_API, NAME)

    response = requests.get('%s' % (GH_REPO), headers=headers)
    if response.status_code == 200:
        response = response.json()

        cnt_pr = len(response)

        return cnt_pr
    else:
        print("error")
        return -1


# 프로젝트 tag 개수 측정하는 함수:
def get_cnt_tag():
    # https://api.github.com/repos/OWNER/REPO/tags
    GH_REPO = '%s/repos/%s/tags' % (GH_API, NAME)

    response = requests.get('%s' % (GH_REPO), headers=headers)

    if response.status_code == 200:
        response = response.json()

        cnt_tag = len(response)

        return cnt_tag
    else:
        print("error")
        return -1


# 프로젝트 release 개수 측정하는 함수:
def get_cnt_release():
    # https://api.github.com/repos/OWNER/REPO/releases
    GH_REPO = '%s/repos/%s/releases' % (GH_API, NAME)

    response = requests.get('%s' % (GH_REPO), headers=headers)

    if response.status_code == 200:
        response = response.json()

        cnt_release = len(response)

        return cnt_release
    else:
        print("error")
        return -1


# 프로젝트 깃허브 활용도 구하는 함수:
def get_score_usability():
    tag = get_cnt_tag()
    release = get_cnt_release()
    pr = get_cnt_pr()
    branch = get_cnt_branch()
    issue = get_cnt_issue()

    score_usability = tag + release + pr + branch + issue

    return score_usability


print(get_score_usability())