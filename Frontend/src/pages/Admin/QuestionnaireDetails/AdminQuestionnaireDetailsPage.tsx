import questionnaireListSampleJson from 'constants/json/questionnaire_list_sample.json'
import questionnaireSampleJson from 'constants/json/questionnaire_sample.json'
import { FC, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { QuestionListType, QuestionnaireItemType, QuestionnaireListType, QuestionType } from 'types/questionnaire'
import { camelizeKey } from 'utils/camelizeKey'
import { decamelizeKey } from 'utils/decamelizeKey'
import { EditableQuestionCard } from './components/EditableQuestionCard'
import {
  ContentContainer,
  HeaderContainer,
  HeaderRoot,
  HeaderTypo,
  QuestionCreateButton,
  QuestionnaireListButton,
  QuestionnaireSubmitButton,
  Root,
} from './styled'

type AdminQuestionnaireDetailsPageProps = {
  className?: string
}

export const AdminQuestionnaireDetailsPage: FC<AdminQuestionnaireDetailsPageProps> = ({ className }) => {
  const navigate = useNavigate()
  const { questionnaireKey = 0 } = useParams()
  // eslint-disable-next-line no-undef
  const localStorageQuestionnaireListData = localStorage.getItem('questionnaire_list_sample')
  const questionnaireList: QuestionnaireListType = localStorageQuestionnaireListData
    ? (camelizeKey(JSON.parse(localStorageQuestionnaireListData)) as QuestionnaireListType)
    : (camelizeKey(questionnaireListSampleJson) as QuestionnaireListType)

  const [questionnaireData, setQuestionnaireData] = useState<QuestionnaireItemType>(
    (questionnaireList.questionnaireList.filter(
      (questionnaireItem) => questionnaireItem.key === +questionnaireKey
    )[0] as QuestionnaireItemType) ?? (camelizeKey(questionnaireSampleJson) as QuestionnaireItemType)
  )

  const onClickQuestionnaireListButton = () => {
    navigate('/admin/questionnaire/list')
  }

  const onChangeQuestionType = (questionKey: number) => (questionType: QuestionType) => () => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey ? { ...questionItem, type: questionType } : questionItem
      )
      return { ...newQuestionnaireData, questionListData: newQuestionListData }
    })
  }

  const onCreateQuestion = () => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionKey = newQuestionnaireData.questionList[newQuestionnaireData.questionList.length - 1].key + 1
      let newQuestionList: QuestionListType = [
        ...prevQuestionnaireData.questionList,
        {
          key: newQuestionKey,
          title: '',
          answer: '0',
          optionList: [
            {
              key: 0,
              title: '질문 1',
            },
          ],
          score: 5,
          type: 'RADIO' as QuestionType,
        },
      ]
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionList }
      return newQuestionnaireData
    })
  }

  const onDeleteQuestion = (questionKey: number) => () => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionList = newQuestionnaireData.questionList.filter((questionItem) => questionItem.key !== questionKey)
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionList }
      return newQuestionnaireData
    })
  }

  const onChangeQuestionTitle = (questionKey: number) => (e: any) => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              title: e.target.value,
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onChangeQuestionScore = (questionKey: number) => (e: any) => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              score: e.target.value,
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onChangeQuestionAnswer = (questionKey: number) => (e: any) => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              answer: e.target.value,
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onCreateOption = (questionKey: number) => () => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newOptionKey =
        newQuestionnaireData.questionList[questionKey].optionList[
          newQuestionnaireData.questionList[questionKey].optionList.length - 1
        ].key + 1
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              optionList: questionItem.optionList.concat({
                key: newOptionKey,
                title: `선택지 ${newOptionKey}`,
              }),
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onDeleteOption = (questionKey: number) => (optionKey: number) => () => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              optionList: questionItem.optionList.filter((optionData) => optionData.key !== optionKey),
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onChangeOption = (questionKey: number) => (optionKey: number) => (e: any) => {
    setQuestionnaireData((prevQuestionnaireData) => {
      let newQuestionnaireData = prevQuestionnaireData
      let newQuestionListData = newQuestionnaireData.questionList.map((questionItem) =>
        questionItem.key === questionKey
          ? {
              ...questionItem,
              optionList: questionItem.optionList.map((optionItem) =>
                optionItem.key === optionKey ? { ...optionItem, title: e.target.value } : optionItem
              ),
            }
          : questionItem
      )
      newQuestionnaireData = { ...newQuestionnaireData, questionList: newQuestionListData }
      return newQuestionnaireData
    })
  }

  const onClickQuestionnaireEditButton = () => {
    let newQuestionnaireListData = questionnaireList
    newQuestionnaireListData.questionnaireList = newQuestionnaireListData.questionnaireList.map((questionnaireItem) =>
      questionnaireItem.key === +questionnaireKey
        ? { ...questionnaireData, version: questionnaireItem.version + 1 }
        : questionnaireItem
    )
    // eslint-disable-next-line no-undef
    localStorage.removeItem('questionnaire_list_sample')
    // eslint-disable-next-line no-undef
    localStorage.setItem('questionnaire_list_sample', JSON.stringify(decamelizeKey(newQuestionnaireListData)))
    // eslint-disable-next-line no-undef
    alert('질문지 수정이 완료되었습니다!')
    // eslint-disable-next-line no-undef
    window.location.reload()
  }

  return (
    <Root className={className}>
      <HeaderRoot>
        <HeaderContainer>
          <HeaderTypo>
            질문지 어드민_{questionnaireData.title} 질문지 수정 (current version : {questionnaireData.version})
          </HeaderTypo>
        </HeaderContainer>
      </HeaderRoot>
      <ContentContainer>
        <QuestionnaireListButton onClick={onClickQuestionnaireListButton}>목록으로</QuestionnaireListButton>
        {questionnaireData.questionList.map((questionItem) => (
          <EditableQuestionCard
            questionData={questionItem}
            onChangeQuestionType={onChangeQuestionType(questionItem.key)}
            onChangeQuestionTitle={onChangeQuestionTitle(questionItem.key)}
            onChangeQuestionScore={onChangeQuestionScore(questionItem.key)}
            onChangeQuestionAnswer={onChangeQuestionAnswer(questionItem.key)}
            onDeleteQuestion={onDeleteQuestion(questionItem.key)}
            onDeleteOption={onDeleteOption(questionItem.key)}
            onCreateOption={onCreateOption(questionItem.key)}
            onChangeOption={onChangeOption(questionItem.key)}
            key={`question_${questionItem.key}`}
          />
        ))}
        <QuestionCreateButton onClick={onCreateQuestion}>질문 추가</QuestionCreateButton>
        <QuestionnaireSubmitButton onClick={onClickQuestionnaireEditButton}>질문지 수정</QuestionnaireSubmitButton>
      </ContentContainer>
    </Root>
  )
}
