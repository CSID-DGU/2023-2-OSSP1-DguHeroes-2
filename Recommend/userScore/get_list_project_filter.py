import pickle

# Team Members 구하는 함수
def get_num_members(NAME):
    # 처음에는 collaborators를 구하려고 했으나, collaborators여도 기여하지 않은 경우 프로젝트 초기에 팀에서 나갔을 수도 있고,
    # collaborators가 아니면서 contributors기만 한 팀원도 있을 수 있다고 생각해 그냥 배제하기로 했다. 대신 contributors에서 필터링.

    #  https://api.github.com/repos/OWNER/REPO/contributors
    GH_REPO = '%s/repos/%s/contributors' % (GH_API, NAME)
    min_rate_commits = 0.05;  # 상대적-5퍼센트 이상은 돼야 팀원으로 인정
    min_cnt_commits = 10;  # 절대적-10개 이상은 돼야 팀원으로 인정
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()
    cnt_contributors = len(response)

    return cnt_contributors


list_project_name_last = []

# 2~8인 사이의 프로젝트만 필터링
# '''
for lan in list(GRAPH_SEARCH_VARIABLE.keys())[6:]:
    print(lan)
    if lan == "c%23":
        lan = "c#"
    elif lan == "c%2B%2B":
        lan = "c++"
    with open('%s_project_list.pkl' % (lan), 'rb') as f:
        list_project_name = pickle.load(f)

        for idx, project_name in enumerate(list_project_name):
            cnt_members = get_num_members(project_name)
            if idx % 50 == 0:
                print(idx / 1000)
            if cnt_members >= 2 and cnt_members <= 8:
                list_project_name_last.append(project_name)

    print(list_project_name)
    with open('%s_project_list_last' % (lan), 'wb') as f:
        pickle.dump(list_project_name_last, f)

# '''

'''
lan = list(GRAPH_SEARCH_VARIABLE.keys())[0]
with open('%s_project_list.pkl'%(lan), 'rb') as f:
    list_project_name = pickle.load(f)

    for project_name in list_project_name:
        cnt_members = get_num_members(GH_API, project_name)
        if cnt_members >= 2 and cnt_members <= 8:
            list_project_name_last.append(project_name)
'''

list_combine = []

for lan in list(GRAPH_SEARCH_VARIABLE.keys()):
    print(lan)
    if lan == "c%23":
        lan = "c#"
    elif lan == "c%2B%2B":
        lan = "c++"
    with open('%s_project_list_last.pkl' % (lan), 'rb') as f:
        list_project_name = pickle.load(f)

        list_combine.extend(list_project_name)

list_combine = list(set(list_combine))
print(len(list_combine))

with open('combine_project_list.pkl', 'wb') as f:
    pickle.dump(list_combine, f)