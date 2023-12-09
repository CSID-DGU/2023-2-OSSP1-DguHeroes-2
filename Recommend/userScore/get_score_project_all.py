list_combine = []
for lan in list(GRAPH_SEARCH_VARIABLE.keys())[:9]:
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

import time
import pickle

GRAPH_SEARCH_VARIABLE = {'javascript' : ['2023-05-01','react'],'html':['2023-11-01','html'], 'css':['2023-11-01','css'],\
                         'python':['2023-11-01','django'],'typescript':['2023-06-01','react'], 'java':['2023-11-01','spring'],\
                         'c%23':['2023-11-01','c'], 'c%2B%2B':['2023-11-01','c%2B%2B'], 'c': ['2023-09-01','c'], 'php': ['2023-10-01','php'],\
                         'go': ['2023-07-01','go'], 'rust': ['2023-07-01','rust'], 'kotlin': ['2023-11-01','kotlin'],\
                         'ruby': ['2023-11-01', 'ruby'], 'lua':['2023-11-01',''], 'dart':['2023-12-01','dart'],\
                         'swift':['2023-11-01',''], 'r':['2023-12-01','']}

'''
list_project_name = []
for lan in list(GRAPH_SEARCH_VARIABLE.keys())[:5]:
    list_project_result = []
    with open('%s_project_list_last.pkl'%(lan), 'rb') as f:
        list_project_name = pickle.load(f)
    for project_name in list_project_name:
        score = get_score_main(project_name)
        list_project_result.append(score)
    with open('%s_project_result_list.pkl'%(lan), 'wb') as f:
        pickle.dump(list_project_name, f)
'''

list_project_result = []
for idx, project_name in enumerate(list_combine):
    if idx>=91:
        score = get_score_main(project_name)
        if score == -1:
            continue
        list_project_result.append(score)
        if idx%20 == 0:
            with open('%s_project_result_list.pkl'%(str(idx/20)), 'wb') as f:
                pickle.dump(list_project_result, f)
                list_project_result = []