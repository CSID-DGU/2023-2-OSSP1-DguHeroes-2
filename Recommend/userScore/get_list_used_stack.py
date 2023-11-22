# q=repo:octocat/Spoon-Knife+css

import base64

LANGUAGE = 'java'

GH_REPO = '%s/search/code?q=language:%s+repo:%s/%s' % (GH_API, LANGUAGE, OWNER, REPO)
response = requests.get('%s' % (GH_REPO), headers=headers)
response = response.json()

# print(response)

cnt_search_result = response['total_count']
print(cnt_search_result)
max_page_num = int((cnt_search_result + 99) / 100)

list_url_file = []

for page in range(max_page_num):
    GH_REPO = '%s/search/code?per_page=100&q=language:%s+repo:%s/%s' % (GH_API, LANGUAGE, OWNER, REPO)
    response = requests.get('%s' % (GH_REPO), headers=headers)
    response = response.json()

    # print(response)

    cnt_search_result_small = len(response['items'])

    for idx in range(cnt_search_result_small):
        list_url_file.append(response['items'][idx]['sha'])

# print(response['items'][0][url])

# print(list_url_file)

SHA = list_url_file[0]

# https://api.github.com/repos/OWNER/REPO/git/blobs/FILE_SHA

GH_REPO = '%s/repos/%s/%s/git/blobs/%s' % (GH_API, OWNER, REPO, SHA)
response = requests.get('%s' % (GH_REPO), headers=headers)
response = response.json()

content = base64.b64decode(response['content'])

print(content)

MAX_LINE_IMPORT = 40 if 40 < len(content) / 2 else len(content) / 2
WORD_MEAN_IMPORT = "import"  # 임시로 지정

used_language = "java"  # 임시로 java라고 설정, 확장자명에거 가져와야 함

WORD_STACK = ["lombok", "test"]  # 임시로 지정, 실제로는 dictionary 형태로 언어를 key로 넣어 해당 언어의 프레임워크
# 라이브러리 등 기술 스택을 리스트로 받아야 한다.
list_score_stack = [0, 0, 0]  # 실제로는 점수를 저장하는 게 아니라, 각종 데이터를 저장해야 한다. 파일 크기의 합이라던가, 나중에 비율을
# 비교하기 위해

list_is_used_stack = []

print("최대 import 검사 줄 : %s" % (MAX_LINE_IMPORT))

code_line = str(content).split('\\n')
for idx, line in enumerate(code_line):  # 한 줄씩 읽기
    if idx <= MAX_LINE_IMPORT:  # import 문이 나오는 상수까지 반복
        if WORD_MEAN_IMPORT in line:  # import 문이 line 에 있다면
            for idx2, stack in enumerate(WORD_STACK):  # 어떤 스택을 import 했는지 확인
                if stack not in list_is_used_stack and stack in line:  # 스택이 파일에 처음 나온다면 리스트에 저장
                    list_is_used_stack.append(stack)
                    continue
if len(list_is_used_stack) == 0:
    list_is_used_stack.append(used_language)

print(line)
print(list_is_used_stack)