import time
import pickle

GRAPH_SEARCH_VARIABLE = {'javascript': ['2023-05-01', 'react'], 'html': ['2023-11-01', 'html'],
                         'css': ['2023-11-01', 'css'], \
                         'python': ['2023-11-01', 'django'], 'typescript': ['2023-06-01', 'react'],
                         'java': ['2023-11-01', 'spring'], \
                         'c%23': ['2023-11-01', 'c'], 'c%2B%2B': ['2023-11-01', 'c%2B%2B'], 'c': ['2023-09-01', 'c'],
                         'php': ['2023-10-01', 'php'], \
                         'go': ['2023-07-01', 'go'], 'rust': ['2023-07-01', 'rust'], 'kotlin': ['2023-11-01', 'kotlin'], \
                         'ruby': ['2023-11-01', 'ruby'], 'lua': ['2023-11-01', ''], 'dart': ['2023-12-01', 'dart'], \
                         'swift': ['2023-11-01', ''], 'r': ['2023-12-01', '']}


# url encoding이 된 결과로 파라미터가 들어가지 않으면 이상한 결과가 나온다. 특수기호를 쓰는 c++과 c#만 url_encoding을 해주면 된다.

# Project ID 구하는 함수
def get_project_id(flanguage, fdict_search):
    list_pr_name = []
    max_page_num = 10
    page_num = 0
    while page_num < max_page_num:
        try:
            # "https://api.github.com/search/repositories?q=Q"
            GH_REPO = '%s/search/repositories?q=language:%s +created:>%s +pushed:<%s +is:public +%s&sort=stars&order=desc&per_page=%s&page=%s' \
                      % (GH_API, flanguage, '2023-01-01', fdict_search[flanguage][0], fdict_search[flanguage][1],
                         MAX_PER_PAGE, page_num)

            response = requests.get('%s' % (GH_REPO), headers=headers)
            response = response.json()
            # print(response)
            for idx, project in enumerate(response['items']):
                list_pr_name.append(project['full_name'])
            print(page_num)
            page_num += 1
        except:
            print("error :" + str(page_num))
            time.sleep(5)

    return list_pr_name


# '''
for lan in list(GRAPH_SEARCH_VARIABLE.keys()):
    list_project_name = get_project_id(lan, GRAPH_SEARCH_VARIABLE)

    print(len(list_project_name))
    print(list_project_name)

    with open('%s_project_list' % (lan), 'wb') as f:
        pickle.dump(list_project_name, f)
# '''

# lan = 'c%2B%2B'
list_project_name = get_project_id(lan, GRAPH_SEARCH_VARIABLE)

print(len(list_project_name))
print(list_project_name)

# '''
with open('%s_project_list.pkl' % (lan), 'wb') as f:
    pickle.dump(list_project_name, f)
# '''
'''
with open('c++_project_list.pkl', 'wb') as f:
    pickle.dump(list_project_name, f)
'''