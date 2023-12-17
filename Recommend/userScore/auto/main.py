from fastapi import FastAPI
from github_score_12 import *
from get_profile import *
from sonar_crawling import *

import pickle

app = FastAPI()

@app.get("/project/{user}/{project_name}")
def get_score(project_name: str, user: str):
    NAME = project_name
    SC_GH_ORG = NAME.split('/')[0]
    SC_GH_REPO = NAME.split('/')[-1]
    response = get_value_metric(SC_GH_ORG, SC_GH_REPO, SC_ORG, METRIC, SC_TOKEN)

    list_project_score_sc = []

    score = response['component']
    dict_project_score_sc = {'project_name': score['key'].replace('_', '/')}
    for measure in score['measures']:
        dict_project_score_sc[measure['metric']] = measure['value']
    list_project_score_sc.append(dict_project_score_sc)

    delete_project(NAME, SC_ORG, SC_TOKEN)
    delete_webhook(NAME.replace("_","/")+"_webhook")

    with open('%s_sonar_data.pkl'%(user), 'rb') as f:
        list_project_score = pickle.load(f)

    list_project_score.extend(list_project_score_sc)

    with open('%s_sonar_data.pkl'%(user), 'wb') as f:
        pickle.dump(list_project_score, f)

    return 0

@app.post("/")
def post_score(name: str):
    list_project_name = get_profile_project_list(name)
    print(list_project_name)
    user = name
    list_project_stack_base = []
    with open('%s_sonar_data.pkl' % (user), 'wb') as f:
        pickle.dump([], f)
    if len(list_project_name) < 1:
        print("프로젝트 수 부족")
        return -1
    else:
        list_project_score_gh = []
        list_user_stack = []
        list_project_name_last = []
        for project_name in list_project_name:
            list_name_members, list_cnt_commits = get_members(project_name)

            if len(list_name_members) < 2:
                print("member 수 부족")
                continue

            print(project_name)
            get_score_sonarcloud(name, project_name)
            dict_project_score, list_project_stack = get_score_main(project_name, name)
            if dict_project_score == -1:
                continue
            list_project_name_last.append(project_name)
            list_project_score_gh.append(dict_project_score)
            list_project_stack_base.append(list_project_stack)
            break
        project_idx = 0
        print(list_project_name_last)
        while project_idx < len(list_project_name_last):
            project_name = list_project_name_last[project_idx]
            try:
                NAME = project_name
                print(NAME)
                SC_GH_ORG = NAME.split('/')[0]
                SC_GH_REPO = NAME.split('/')[-1]
                response = get_value_metric(SC_GH_ORG, SC_GH_REPO, SC_ORG, METRIC, SC_TOKEN)

                list_project_score_sc = []

                score = response['component']
                dict_project_score_sc = {'project_name': score['key'].replace('_', '/')}
                for measure in score['measures']:
                    dict_project_score_sc[measure['metric']] = measure['value']
                list_project_score_sc.append(dict_project_score_sc)

                delete_project(NAME, SC_ORG, SC_TOKEN)
                #delete_webhook(NAME.replace("_", "/") + "_webhook")

                with open('%s_sonar_data.pkl' % (user), 'rb') as f:
                    list_project_score = pickle.load(f)

                list_project_score.extend(list_project_score_sc)

                with open('%s_sonar_data.pkl' % (user), 'wb') as f:
                    pickle.dump(list_project_score, f)
                project_idx += 1
            except Exception as e:
                print(e)
                time.sleep(30)

        github_dict = {'project_name': [], 'popularity_watch': [], 'popularity_star': [], 'popularity_fork': [], 'usability_issue': [], \
              'usability_branch': [], 'usability_pr': [], 'usability_tag': [], 'usability_release': [], 'commit_rate_std': [], 'project_size': []}

        for project in list_project_score_gh:  # you can list as many input dicts as you want here
            for key, value in project.items():
                github_dict[key].append(value)

        with open('%s_sonar_data.pkl'%(name), 'rb') as f:
            list_project_score_sc = pickle.load(f)

        sonar_dict = {'project_name': [], 'complexity': [], 'bugs': [], 'duplicated_lines_density': [], 'code_smells': [], \
               'comment_lines': [], 'cognitive_complexity': [], 'vulnerabilities': []}

        for project in list_project_score_sc:  # you can list as many input dicts as you want here
            for key, value in project.items():
                sonar_dict[key].append(value)

        # common_gh_sc_list = list(set(sonar_dict['project_name']) & set(github_dict['project_name']))

        sonar_dict.update(github_dict)
        common_gh_sc_dict = sonar_dict

        for key in list(common_gh_sc_dict.keys()):
            if len(common_gh_sc_dict[key]) != len(common_gh_sc_dict['project_name']):
                diff = len(common_gh_sc_dict['project_name']) - len(common_gh_sc_dict[key])
                for i in range(diff):
                    common_gh_sc_dict[key].append(0)

        print(common_gh_sc_dict)

        '''
        common_gh_sc_dict = {'project_name': [], 'complexity': [], 'bugs': [], 'duplicated_lines_density': [],'code_smells': [], \
                             'comment_lines': [], 'cognitive_complexity': [], 'vulnerabilities': [],'popularity_watch': [], \
                             'popularity_star': [], 'popularity_fork': [], 'usability_issue': [],'usability_branch': [], \
                             'usability_pr': [], 'usability_tag': [], 'usability_release': [], 'commit_rate_std': [], 'project_size': []}

        for common_project in common_gh_sc_list:
            sonar_idx = sonar_dict['project_name'].index(common_project)
            github_idx = github_dict['project_name'].index(common_project)
            common_gh_sc_dict['project_name'].append(common_project)
            for key in list(sonar_dict.keys()):
                if key != 'project_name':
                    common_gh_sc_dict[key].append(sonar_dict[key][sonar_idx])
            for key in list(github_dict.keys()):
                if key != 'project_name':
                    common_gh_sc_dict[key].append(github_dict[key][github_idx])
        '''

        df_sc_gh = pd.DataFrame(common_gh_sc_dict)
        print(df_sc_gh)

        with open('project_data_last_real.pkl', 'rb') as f:
            normalize_data = pickle.load(f)
        df = pd.DataFrame(normalize_data)

        df_concat = pd.concat([df,df_sc_gh], ignore_index=True)

        import numpy as np

        test_gh_sc_dict = {'complexity1': [], 'bugs_rate': [], 'duplication_rate': [], 'code_smells_rate': [], \
                           'comment_rate': [], 'complexity2': [], 'vulnerabilities_rate': [], 'popularity_watch': [], \
                           'popularity_star': [], 'popularity_fork': [], 'usability_issue': [], 'usability_branch': [], \
                           'usability_pr': [], 'usability_tag': [], 'usability_release': [], 'commit_rate_std': [],
                           'project_size': []}

        print(df_concat)

        df_concat2 = pd.DataFrame()

        df_concat2['project_name'] = df_concat['project_name']
        df_concat2['comment_rate'] = df_concat['comment_lines'].astype(int) / df_concat['project_size']
        df_concat2['bugs_rate'] = -1 * df_concat['bugs'].astype(int) / df_concat['project_size']
        df_concat2['code_smells_rate'] = -1 * df_concat['code_smells'].astype(int) / df_concat['project_size']
        df_concat2['vulnerabilities_rate'] = -1 * df_concat['vulnerabilities'].astype(int) / df_concat['project_size']
        df_concat2['duplication_rate'] = -1 * df_concat['duplicated_lines_density'].astype(float)
        df_concat2['complexity1'] = -1 * df_concat['complexity'].astype(float)
        df_concat2['complexity2'] = -1 * df_concat['cognitive_complexity'].astype(float)
        df_concat2['popularity_watch'] = df_concat['popularity_watch'].astype(float)
        df_concat2['popularity_star'] = np.log(df_concat['popularity_star']).astype(float)
        df_concat2['popularity_fork'] = df_concat['popularity_fork'].astype(float)
        df_concat2['usability_pr'] = df_concat['usability_pr'].astype(float)
        df_concat2['usability_tag'] = df_concat['usability_tag'].astype(float)
        df_concat2['usability_release'] = df_concat['usability_release'].astype(float)
        df_concat2['usability_issue'] = df_concat['usability_issue'].astype(float)
        df_concat2['usability_branch'] = df_concat['usability_branch'].astype(float)
        df_concat2['commit_rate_std'] = df_concat['commit_rate_std'].astype(float)
        df_concat2['project_size'] = df_concat['project_size'].astype(float)

        for key in list(test_gh_sc_dict.keys()):
            df_concat2 = detect_outliers(df_concat2, key, 15)

        list_name_df = df_concat2['project_name']
        df_concat2 = df_concat2.drop('project_name', axis=1)

        print(df_concat2)

        df_normalization = (df_concat2 - df_concat2.min()) / (df_concat2.max() - df_concat2.min())

        df_normalization['project_name'] = list_name_df
        dict_stack_score = dict()
        dict_stack_score_last = dict()

        ##list_project_name_last = ['AcmeSoftwareLLC/uniform', 'facebookincubator/dataclassgenerate']
        #list_project_stack = [['dart', 'html', 'swift'], ['html', 'kotlin']]

        list_all_stack = []
        for idx, project_name in enumerate(list_project_name_last):
            list_all_stack.extend(list_project_stack_base[idx])
        list_all_stack = list(set(list_all_stack))

        print(list_all_stack)
        for stack in list_all_stack:
            dict_stack_score[stack] = []
        # print(dict_stack_score)

        for idx, project_name in enumerate(list_project_name_last):
            filter = (df_normalization.project_name == project_name)
            df_last = df_normalization.loc[filter, :]
            df_last = df_last.fillna(0)
            print(list(df_last.columns))
            project_score = df_last['comment_rate'] + df_last['bugs_rate'] + df_last['code_smells_rate'] + df_last['vulnerabilities_rate'] + \
                            df_last['duplication_rate'] + 0.5 * df_last['complexity1'] + 0.5 * df_last['complexity2'] + df_last['popularity_watch'] / 3 + \
                            df_last['popularity_star'] / 3 + df_last['popularity_fork'] / 3 + 0.2 * df_last['usability_pr'] + 0.2 * df_last['usability_tag'] + \
                            0.2 * df_last['usability_release'] + 0.2 * df_last['usability_issue'] + 0.2 * df_last['usability_branch'] + \
                            df_last['commit_rate_std'] + df_last['project_size']
            try:
                project_score = round(float(project_score), 3)
            except:
                project_score = 5.0
            print(project_score)
            list_used_stack = list_project_stack_base[idx]
            for stack in list_used_stack:
                dict_stack_score[stack].append(project_score)
        for stack in list_all_stack:
            dict_stack_score_last[stack] = sum(dict_stack_score[stack]) / len(dict_stack_score[stack])

        print(dict_stack_score_last)

        list_in_dict = []
        for key in list(dict_stack_score_last.keys()):
            list_in_dict.append({'githubId' : name, 'stack' : key, 'score' : dict_stack_score_last[key]})
        dict_last = {'data' : list_in_dict}

        response = requests.post('user/put/newscore', data=dict_last)

        #return dict_stack_score_last
        print(dict_last)
        print("end")
        return 0




