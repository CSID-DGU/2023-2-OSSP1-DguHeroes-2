import pickle
import os
from get_score_sonarcloud import *

path = "./github-home2"
file_list = os.listdir(path)
# 현재 디렉토리내에 모든 파일 출력
print(file_lst)  # ['test1.text','test2.text','test3.text','.ipynb_checkpoints','test4.text','test.ipynb','test5.text']

list_file_home = []

for file in file_list:
    with open('%s/%s' % (path, file), 'rb') as f:
        list_project_name = pickle.load(f)
        list_file_home.extend(list_project_name)

# list_file_home = list(set(list_file_home))

print(list_file_home)
print(len(list_file_home))

path = "./github-notebook"
file_list = os.listdir(path)
# 현재 디렉토리내에 모든 파일 출력
print(file_lst)  # ['test1.text','test2.text','test3.text','.ipynb_checkpoints','test4.text','test.ipynb','test5.text']

list_file_notebook = []

for file in file_list:
    with open('%s/%s' % (path, file), 'rb') as f:
        list_project_name = pickle.load(f)
        list_file_notebook.extend(list_project_name)

# list_file_home = list(set(list_file_home))

print(list_file_notebook)
print(len(list_file_notebook))

list_file_all = list_file_home + list_file_notebook

print(len(list_file_all))

dd = {'project_name': [], 'popularity_watch': [], 'popularity_star': [], 'popularity_fork': [], 'usability_issue': [], \
      'usability_branch': [], 'usability_pr': [], 'usability_tag': [], 'usability_release': [], 'commit_rate_std': [], \
      'project_size': []}

for d in list_file_all:  # you can list as many input dicts as you want here
    for key, value in d.items():
        dd[key].append(value)

print(dd)

import time

list_score_project_sc = []
list_done_project_sc = []
list_undone_project_sc = []
for idx, file in enumerate(list_file_name):

    print(idx)
    NAME = file
    SC_GH_ORG = NAME.split('/')[0]
    SC_GH_REPO = NAME.split('/')[-1]
    SC_GH_KEY = str(get_project_id_gh2sc(GITHUB_API_TOKEN, NAME))

    response = get_value_metric(SC_GH_ORG, SC_GH_REPO, SC_ORG, METRIC, SC_TOKEN)
    time.sleep(3)
    if len(response['component']['measures']) > 0:
        list_done_project_sc.append(response['component']['key'])
        list_score_project_sc.append(response)
    else:
        list_undone_project_sc.append(response['component']['key'])

print(len(list_score_project_sc))
with open('./sonarcube/1_project_result_list.pkl', 'wb') as f:
    pickle.dump(list_score_project_sc, f)


# 프로젝트 지우는 함수
def delete_project(key, SC_ORG, SC_TOKEN):
    params = {
        'project': key,
    }

    response = requests.post(
        'https://sonarcloud.io/api/projects/delete',
        params=params,
        auth=(SC_TOKEN, ''),
    )

    if (is_json(response)):
        response = response.json()
    print(response)
    return response


print(list_done_project_sc)
for done_project in list_done_project_sc:
    delete_project(done_project, SC_ORG, SC_TOKEN)

with open('./sonarcube/1_project_result_list.pkl', 'rb') as f:
    list_project_score_pkl = pickle.load(f)
    # print(list_project_score_sc)

list_project_score_sc = []

for idx, project_score_sc in enumerate(list_project_score_pkl):
    if idx >= 0:
        score = project_score_sc['component']
        dict_project_score_sc = {'project_name': score['key'].replace('_', '/')}
        for measure in score['measures']:
            dict_project_score_sc[measure['metric']] = measure['value']
        list_project_score_sc.append(dict_project_score_sc)

print(list_project_score_sc)