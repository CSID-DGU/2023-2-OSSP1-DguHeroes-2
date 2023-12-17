from common_variable import *
from github_score_3 import *
from github_score_4 import *
from github_score_5 import *

# Project 사용 언어 구하는 함수
def get_language(fname):
    NAME = fname
    # https://api.github.com/repos/OWNER/REPO/languages
    GH_REPO = '%s/repos/%s/languages' % (GH_API, NAME)
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()
    list_language = list(response.keys())
    list_language = [lang.lower() for lang in list_language]
    list_language = [lang for lang in list_language if lang in GRAPH_LANGUAGE]
    print(list_language)

    list_not_language = [x for x in GRAPH_LANGUAGE_S if x not in list_language]
    idx_extension = 0
    while idx_extension < len(list_not_language):
        lan = list_not_language[idx_extension]

        if lan == "c#":
            lan = "c%23"
        elif lan == "c++":
            lan = "c%2B%2B"
        try:
            # print(lan)
            GH_REPO = '%s/search/code?q=language:%s +repo:%s' % (GH_API, lan, NAME)

            response = requests.get('%s' % (GH_REPO), headers=headers)
            response = response.json()
            # print(response)

            if len(response['items']) != 0:
                list_language.append(lan)
            idx_extension += 1
        except Exception as e:
            print("error check_lan:" + str(lan) + "/" + str(idx_extension))
            print(e)
            time.sleep(5)

    return list_language


# Team Members 구하는 함수 + Commit 개수 구하는 함수
def get_members(fname):
    # 처음에는 collaborators를 구하려고 했으나, collaborators여도 기여하지 않은 경우 프로젝트 초기에 팀에서 나갔을 수도 있고,
    # collaborators가 아니면서 contributors기만 한 팀원도 있을 수 있다고 생각해 그냥 배제하기로 했다. 대신 contributors에서 필터링.

    NAME = fname
    #  https://api.github.com/repos/OWNER/REPO/contributors
    GH_REPO = '%s/repos/%s/contributors' % (GH_API, NAME)
    min_rate_commits = 0.05;  # 상대적-5퍼센트 이상은 돼야 팀원으로 인정
    min_cnt_commits = 10;  # 절대적-10개 이상은 돼야 팀원으로 인정
    response = requests.get('%s' % (GH_REPO), headers=headers)
    try:
        response = response.json()
        cnt_contributors = len(response)
        list_name_members = []
        list_cnt_commits = []

        for user in response:
            '''
            if user['login'] == 'github-actions[bot]':
                print("github-action")
                continue
            '''
            list_name_members.append(user['login'])
            list_cnt_commits.append(user['contributions'])

        list_remove_members = []
        list_remove_commits = []
        sum_commits = sum(list_cnt_commits)
        for idx, user in enumerate(list_name_members):
            if list_cnt_commits[idx] / sum_commits < min_rate_commits or list_cnt_commits[idx] < min_cnt_commits:
                # del 이 pop보다 미세하게 빠름
                list_remove_members.append(user)
                list_remove_commits.append(list_cnt_commits[idx])

        list_name_members_copy = [name for name in list_name_members if name not in list_remove_members]
        list_cnt_commits_copy = [name for name in list_cnt_commits if name not in list_remove_commits]

        print(list_name_members_copy)

        return list_name_members_copy, list_cnt_commits_copy
    except Exception as e:
        print(e)
        return [], []


# Commit Code 가져오는 함수 + 추가한 라인 수도 카운트한 뒤 합해서 전달 (깃허브 점수 2번 구하기 위해)
def get_commit_code(flist_language, list_furl, fname):
    list_language = flist_language

    NAME = fname
    is_initial_commit = 0  # 초기에 다른 프로젝트를 클론해 엄청난 양의 코드를 커밋한 게 맞는지 체크

    list_furl.reverse()

    list_addition_code = []
    list_filename_language = []
    cnt_addition = []
    # cnt_deletion = []
    len_url = len(list_furl)
    idx = 0
    while idx < len_url:
        # print(furl)
        furl = list_furl[idx]
        try:
            response = requests.get('%s' % (furl), headers=headers)
            response = response.json()

            # print(response['files'])

            if response['commit']['verification']['verified'] == True:
                # print("verified")
                idx += 1
                continue

            # 초기에 다른 프로젝트 기반 복제를 했을 때 가져오게 되는 엄청난 양의 addition은 무시
            if response['stats']['additions'] - response['stats']['deletions'] > 5000 and \
                    is_initial_commit == 0 and len(response['files']) > 20:
                print("initial file")
                is_initial_commit = response['stats']['additions']
                # print(furl)
                idx += 1
                continue

            for idx_file, file in enumerate(response['files']):
                filename = file['filename'].split('.')[-1]
                list_extension = get_list_extension(list_language)
                # print(filename)
                # print(list_extension)
                # print(filename in list_extension)
                if filename in list_extension:
                    # status가 added, deleted, renamed 등 다양하게 있는데 이중 코드를 수정한 경우만 가져와야 하므로
                    # status added를 가져오겠다.
                    if (file['status'] == 'added' or file['status'] == 'modified') and file['additions'] > 0:
                        # list_addition_code.append(file['additions'])
                        list_filename_language.append(filename)
                        cnt_addition.append(file['additions'])
                        # cnt_deletion.append(file['deletions'])
                        # print(file)
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
            idx += 1
        except Exception as e:
            print("error idx_code:" + str(idx))
            print(e)
            time.sleep(5)

    return list_addition_code, list_filename_language, cnt_addition, is_initial_commit
    # return list_addition_code, list_filename_language, cnt_addition, cnt_deletion, is_initial_commit


# Commit SHA값 포함된 주소 가져오는 함수
def get_commit_sha(fauthor, fsum, fname):
    NAME = fname
    max_page_num = int(fsum + 99 / 100)  # 실제
    # max_page_num = 1 #용량 줄이려고 테스트용
    list_url_commits = []
    page = 1
    while page < max_page_num + 1:
        try:
            GH_REPO = '%s/repos/%s/commits?per_page=%s&page=%s&author=%s' % (GH_API, NAME, MAX_PER_PAGE, page, fauthor)

            response = requests.get('%s' % (GH_REPO), headers=headers)
            response = response.json()

            cnt_commits = len(response)

            for idx in range(cnt_commits):
                list_url_commits.append(response[idx]['url'])
            page += 1
        except Exception as e:
            print("error idx_sha_page:" + str(page))
            print(e)
            time.sleep(5)

    return list_url_commits


def get_list_extension(flist_language):  # 파일의 확장자를 구하는 함수
    list_extension = []
    for lang in flist_language:
        list_extension.extend(list_language_extension[GRAPH_LANGUAGE.index(lang)])
    # print(list_extension)
    return list_extension


def get_list_file_stack(flist_language, fname, fuser):  # search api 사용해서 기술 스택을 추출하는 함수
    # GH_REPO='%s/search/issues?q=repo:%s/%s+type:pr'%(GH_API, OWNER, REPO)
    # GET https://api.github.com/repos/:owner/:repo/commits?path=FILE_PATH

    list_language = flist_language
    NAME = fname

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
        try:
            lang = flist_language[idx_lang]
            if lang not in list_search:
                idx_lang += 1
                continue
            GH_REPO = '%s/search/code?q=%s +repo:%s' % (GH_API, lang, NAME)

            response = requests.get('%s' % (GH_REPO), headers=headers)
            response = response.json()

            len_file = len(response['items'])
            idx_file = 0
            list_file = response['items']

            while idx_file < len_file:
                try:
                    file = list_file[idx_file]
                    file_path = file['path']
                    # print(file_path)

                    GH_REPO = '%s/repos/%s/commits?path=%s&author=%s' % (GH_API, NAME, file_path, fuser)

                    response = requests.get('%s' % (GH_REPO), headers=headers)
                    response = response.json()

                    if len(response) > 0:
                        list_user_language.append(lang)
                        if lang in list_search:
                            list_search.remove(lang)
                            print("search keyword : " + str(list_search))
                        break  # 특정 언어를 쓴 모든 파일을 검색해서, 관련된 커밋 중 committer가 찾는 유저인 게 있다면 for문 종료
                    idx_file += 1
                except Exception as e:
                    print("error idx_file1:" + str(idx_file))
                    print(e)
                    time.sleep(5)
            # print("language_list:"+str(list_user_language))
            idx_lang += 1
            if lang in list_search:
                list_search.remove(lang)
                print("search keyword : " + str(list_search))
        except Exception as e:
            print("error idx_lang1:" + str(idx_lang))
            print(e)
            time.sleep(5)

    print("next2")
    idx_lang = 0
    while idx_lang < len(flist_language):  # 프로젝트 사용 언어로만 반복
        try:
            lang = flist_language[idx_lang]
            list_stack = GRAPH_STACK_TREE[lang]
            # print("사용가능한 총 스택:"+str(list_stack))
            for stack in list_stack:
                if stack not in list_search:  # 이미 유저가 쓰는 기술스택 리스트에 해당 스택이 있으면 패스.
                    continue
                list_stack_keyword = GRAPH_KEYWORD_TREE[stack]
                GH_REPO = '%s/search/code?q=%s +%s +repo:%s' % (
                GH_API, list_stack_keyword[0], list_stack_keyword[1], NAME)

                response = requests.get('%s' % (GH_REPO), headers=headers)
                response = response.json()

                # print(response)

                len_file = len(response['items'])  # 결과가 없으면 response 자체가 3가지 key를 가진 빈 리스토로 나온다.
                idx_file = 0
                list_file = response['items']

                if len_file < MIN_CNT_FILE:
                    if stack in list_search:
                        list_search.remove(stack)
                        print("search keyword : " + str(list_search))
                    continue

                while idx_file < len_file:
                    try:
                        file = list_file[idx_file]
                        file_path = file['path']

                        # print(file_path)

                        GH_REPO = '%s/repos/%s/commits?path=%s&author=%s' % (GH_API, NAME, file_path, fuser)

                        response = requests.get('%s' % (GH_REPO), headers=headers)
                        response = response.json()

                        # print(response)
                        # print(len(response))
                        if len(response) > 0:  # 결과가 없으면 response 자체가 빈 리스트로 나온다.
                            list_user_stack.append(stack)
                            if stack in list_search:
                                list_search.remove(stack)
                                print("search keyword : " + str(list_search))
                            break  # 특정 프레임워크를 쓴 모든 파일을 검색해서, 관련된 커밋 중 committer가 찾는 유저인 게 있다면 for문 종료
                        idx_file += 1
                    except Exception as e:
                        print("error idx_file:" + str(idx_file))
                        print(e)
                        time.sleep(5)
                if lang in list_search:
                    list_search.remove(stack)
                    print("search keyword : " + str(list_search))
            # print("stack_list:"+str(list_user_stack))
            idx_lang += 1
        except Exception as e:
            print("error idx_lang:" + str(idx_lang))
            print(e)
            time.sleep(5)
    print(list_user_language)
    if len(list_user_language) == 0:
        list_user_stack.extend(list_language)
    else:
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


# 이상치 제거하는 함수
def detect_outliers(df, columns, rate):
    MIN_LEN_CODE = 300
    q1 = df[columns].quantile(0.25)
    q3 = df[columns].quantile(0.75)
    # print(q1)
    # print(q3)
    iqr = q3 - q1
    # print(iqr)

    boundary = rate * iqr
    # print(boundary)

    index1 = df[(df[columns] > q3 + boundary) & (df[columns] > MIN_LEN_CODE)].index
    # print(index1)
    # print(index2)

    df[columns] = df[columns].drop(index1)

    return df


def get_score_project(fdict_user, flist_language, fname, fuser):
    NAME = fname

    list_name_members = list(fdict_user.keys())
    sum_project_size = 0
    sum_cnt_annotation = 0
    list_user_code_size = []

    print("볼륨과 비율 구하는 중")
    for member in list_name_members:
        '''
        if fdict_user[member]['initial'] == 0:
            sum_cnt_code = sum(fdict_user[member]['cnt_addition']) - sum(fdict_user[member]['cnt_deletion'])
        elif fdict_user[member]['initial'] != 0:
            rate_initial = fdict_user[member]['initial'] / (fdict_user[member]['initial'] + \
                                                                      sum(fdict_user[member]['cnt_addition']))
            print(rate_initial)
            sum_cnt_code = sum(fdict_user[member]['cnt_addition']) - \
            (sum(fdict_user[member]['cnt_deletion']) - 0.5 * rate_initial * (sum(fdict_user[member]['cnt_deletion'])))
        '''
        print(fdict_user[member]['cnt_addition'])
        df_addition = pd.DataFrame({'cnt_addition': list(fdict_user[member]['cnt_addition'])})
        df_addition = detect_outliers(df_addition, 'cnt_addition', 30)
        df_addition = df_addition.dropna(axis=0)
        # print(df_addition)

        sum_cnt_code = sum(df_addition['cnt_addition'].values.tolist())
        sum_project_size += sum_cnt_code
        # sum_cnt_annotation += fdict_user[member]['annotation']
        list_user_code_size.append(sum_cnt_code)

    if sum_project_size == 0:
        return {}, []

    list_user_code_size = [size / sum_project_size for size in list_user_code_size]
    user_code_std = np.std(np.array(list_user_code_size))

    print("관심도 구하는 중")
    popularity_watch, popularity_star, popularity_fork = get_cnt_popularity(NAME)
    print("활용도 구하는 중")
    usability_issue, usability_branch, usability_pr, usability_tag, usability_release = get_cnt_usability(NAME)
    print("주석 비율 구하는 중")
    # annotation_rate = sum_cnt_annotation/sum_project_size

    dict_score_db = dict()
    dict_score_db['project_name'] = NAME
    used_stack = get_list_file_stack(flist_language, NAME, fuser)
    #dict_score_db['used_stack'] = get_list_file_stack(flist_language, NAME)
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
    # dict_score_db['annotation_rate'] = annotation_rate

    print(dict_score_db)
    return dict_score_db, used_stack


# ---

# Main

def get_score_main(fname, fuser):
    test = {}
    used_stack = []
    NAME = fname
    print(NAME)

    list_project_score = []

    list_name_members = []
    list_cnt_commits = []
    sum_commits = 0

    dict_user_commit = dict()

    list_name_members, list_cnt_commits = get_members(NAME)

    if len(list_name_members) < 2:
        print("member 수 부족")
        return {}, []

    list_language = get_language(NAME)
    print(list_language)

    for idx, member in enumerate(list_name_members):
        list_url_commit = get_commit_sha(member, list_cnt_commits[idx], NAME)
        # list_commit_code, list_filename, list_cnt_addition, list_cnt_deletion, is_initial = get_commit_code(list_url_commit)
        list_commit_code, list_filename, list_cnt_addition, is_initial = get_commit_code(list_language, list_url_commit,
                                                                                         NAME)
        # dict_user_commit[member] = {'code': [], 'cnt_addition': [], 'cnt_deletion': [], 'initial': 0}
        dict_user_commit[member] = {'code': [], 'cnt_addition': [], 'initial': 0}
        dict_user_commit[member]['code'] = list_commit_code  # 멤버별로 커밋 코드를 포함하는 딕셔너리 생성하기
        dict_user_commit[member]['cnt_addition'] = list_cnt_addition  # 깃허브 점수 1,2번, 커밋 비율 구할 때 이용
        # dict_user_commit[member]['cnt_deletion'] = list_cnt_deletion #깃허브 점수 1,2번, 커밋 비율 구할 때 이용
        dict_user_commit[member]['initial'] = is_initial

    test, used_stack = get_score_project(dict_user_commit, list_language, NAME, fuser)
    if len(test) == 0:
        return {}, []
    return test, used_stack