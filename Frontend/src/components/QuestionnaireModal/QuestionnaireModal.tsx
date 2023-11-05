import { Question } from 'components/Question/Question'
import questionnaireListSampleJson from 'constants/json/questionnaire_list_sample.json'
import questionnaireSampleJson from 'constants/json/questionnaire_sample.json'
import { getDevelopmentKeyByString } from 'constants/project/developmentStack'
import { FC, useEffect, useState } from 'react'
import { DevelopmentStackType } from 'types/project'
import {
  QuestionAnswerSheetListType,
  QuestionnaireItemType,
  QuestionnaireListType,
  QuestionType,
} from 'types/questionnaire'
import { camelizeKey } from 'utils/camelizeKey'
import { gradeQuestionnaire } from 'utils/gradeQuestionnaire'
import { QuestionContainer, Root, SubmitButton } from './styled'

type QuestionnaireModalProps = {
  className?: string
  open: boolean
  developmentStack: DevelopmentStackType
  onCloseModal: () => void
  // eslint-disable-next-line no-unused-vars
  onSubmitQuestionnaireAnswerSheet: (score: number) => void
}

export const QuestionnaireModal: FC<QuestionnaireModalProps> = ({
  className,
  open,
  developmentStack,
  onCloseModal,
  onSubmitQuestionnaireAnswerSheet,
}) => {
  // eslint-disable-next-line no-undef
  const localStorageQuestionnaireListData = localStorage.getItem('questionnaire_list_sample')
  const questionnaireList: QuestionnaireListType = localStorageQuestionnaireListData
    ? (camelizeKey(JSON.parse(localStorageQuestionnaireListData)) as QuestionnaireListType)
    : (camelizeKey(questionnaireListSampleJson) as QuestionnaireListType)

  const questionnaireKey = getDevelopmentKeyByString(developmentStack)

  const [questionnaireData] = useState<QuestionnaireItemType>(
    (questionnaireList.questionnaireList.filter(
      (questionnaireItem) => questionnaireItem.key === questionnaireKey
    )[0] as QuestionnaireItemType) ?? (camelizeKey(questionnaireSampleJson) as QuestionnaireItemType)
  )
  const [questionAnswerSheetListData, setQuestionAnswerSheetListData] = useState<QuestionAnswerSheetListType>()

  const onChangeQuestionAnswerSheet =
    (questionKey: number, questionType: QuestionType) => (optionKey: number) => () => {
      setQuestionAnswerSheetListData((prev) => {
        if (prev) {
          let newAnswer = prev?.filter((value) => value.questionKey === questionKey)[0].answer
          if (newAnswer) {
            if (questionType === 'RADIO') {
              newAnswer = [optionKey]
              return prev.map((value) => (value.questionKey === questionKey ? { ...value, answer: newAnswer } : value))
            }
            if (questionType === 'SELECT') {
              if (newAnswer.includes(optionKey)) {
                newAnswer = newAnswer.filter((value) => value !== optionKey)
              } else {
                newAnswer = newAnswer.concat(optionKey)
              }
              return prev.map((value) => (value.questionKey === questionKey ? { ...value, answer: newAnswer } : value))
            }
          }
        }
        return prev
      })
    }

  const onClickSubmitButton = () => {
    if (questionAnswerSheetListData) {
      // eslint-disable-next-line no-undef
      const grade = gradeQuestionnaire(questionnaireData, questionAnswerSheetListData)
      onSubmitQuestionnaireAnswerSheet(grade)
      onCloseModal()
    }
  }

  useEffect(() => {
    setQuestionAnswerSheetListData(
      questionnaireData.questionList.map((questionItem) => ({ questionKey: questionItem.key, answer: [] }))
    )
  }, [open])

  return (
    <Root
      className={className}
      title={`퀴즈 질문지(${questionnaireData.title} version: ${questionnaireData.version})`}
      open={open}
      onCancel={onCloseModal}
      footer={
        <SubmitButton type="primary" onClick={onClickSubmitButton}>
          제출하기
        </SubmitButton>
      }
    >
      <QuestionContainer>
        {questionAnswerSheetListData &&
          questionnaireData.questionList.map((questionItem, index) => (
            <Question
              questionItem={questionItem}
              questionAnswerData={questionAnswerSheetListData[index].answer}
              onChangeQuestionAnswerSheet={onChangeQuestionAnswerSheet(questionItem.key, questionItem.type)}
              key={`question_${questionItem.key}`}
            />
          ))}
      </QuestionContainer>
    </Root>
  )
}
