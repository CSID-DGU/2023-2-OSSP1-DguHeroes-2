import pickle
import os
import pandas as pd

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

list_project_score_sc_new = []

for score in list_project_score_sc:
    if len(list(score.keys())) == 8:
        list_project_score_sc_new.append(score)

print(list_project_score_sc_new)

ddd = {'project_name': [], 'complexity': [], 'bugs': [], 'duplicated_lines_density': [], 'code_smells': [],
       'comment_lines': [], \
       'cognitive_complexity': [], 'vulnerabilities': []}

for d in list_project_score_sc_new:  # you can list as many input dicts as you want here
    for key, value in d.items():
        ddd[key].append(value)

print(ddd)

common_gh_sc_list = []

common_gh_sc_list = list(set(dd['project_name']) & set(ddd['project_name']))

print(len(common_gh_sc_list))

common_gh_sc_dict = {'project_name': [], 'complexity': [], 'bugs': [], 'duplicated_lines_density': [],
                     'code_smells': [], \
                     'comment_lines': [], 'cognitive_complexity': [], 'vulnerabilities': [], 'popularity_watch': [], \
                     'popularity_star': [], 'popularity_fork': [], 'usability_issue': [], 'usability_branch': [], \
                     'usability_pr': [], 'usability_tag': [], 'usability_release': [], 'commit_rate_std': [], \
                     'project_size': []}
common_ddd_idx_list = []
common_dd_idx_list = []

for common_project in common_gh_sc_list:
    dd_idx = dd['project_name'].index(common_project)
    ddd_idx = ddd['project_name'].index(common_project)
    common_gh_sc_dict['project_name'].append(common_project)
    for key in list(ddd.keys()):
        if key != 'project_name':
            # print("idx"+str(ddd_idx))
            # print("len"+str(len(ddd[key])))
            common_gh_sc_dict[key].append(ddd[key][ddd_idx])
    for key in list(dd.keys()):
        if key != 'project_name':
            common_gh_sc_dict[key].append(dd[key][dd_idx])

print(common_gh_sc_dict)

with open('project_data_last.pkl', 'wb') as f:
    pickle.dump(common_gh_sc_dict, f)

new_df = pd.DataFrame(common_gh_sc_dict)
print(new_df)

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