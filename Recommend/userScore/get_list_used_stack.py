
content = dict_user_commit['rlJzr'][0]
code_line = str(content).split('\n')

MAX_LINE_IMPORT = 40 if 40 < len(code_line)/2 else int(len(code_line)/2)
WORD_MEAN_IMPORT = "import" #임시로 지정

used_language = 'java' #임시로 java라고 설정, 확장자명에거 가져와야 함

WORD_STACK =  ['lombok', 'test']#임시로 지정, 실제로는 dictionary 형태로 언어를 key로 넣어 해당 언어의 프레임워크
#라이브러리 등 기술 스택을 리스트로 받아야 한다.
#WORD_STACK = list_language_extension[GRAPH_LANGUAGE.index(used_language)]
list_score_stack = [0, 0] #실제로는 점수를 저장하는 게 아니라, 각종 데이터를 저장해야 한다. 파일 크기의 합이라던가, 나중에 비율을
# 비교하기 위해

list_is_used_stack = []

print("최대 import 검사 줄 : %s"%(MAX_LINE_IMPORT))

for idx, line in enumerate(code_line): #한 줄씩 읽기
    print(line)
    if idx <= MAX_LINE_IMPORT: #import 문이 나오는 상수까지 반복
        if WORD_MEAN_IMPORT in line: #import 문이 line 안에 있다면
            for idx2, stack in enumerate(WORD_STACK): #어떤 스택을 import 했는지 확인
                if stack not in list_is_used_stack and stack in line: #스택이 파일에 처음 나온다면 리스트에 저장
                    list_is_used_stack.append(stack)
                    continue
#if len(list_is_used_stack) == 0: #핵심 프레임워크나 라이브러리 사용이 없다면
#    list_is_used_stack.append(used_language) #일단 이 코드는 필요없을 듯. 어차피 확장자명 체크할 때 사용 언어는 +되므로


print(list_is_used_stack)