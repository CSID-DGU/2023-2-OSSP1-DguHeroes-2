/* eslint-disable no-unused-vars */
import { FC } from 'react'
import { QuestionItemType, QuestionType } from 'types/questionnaire'
import {
  AnswerContainer,
  ContentInput,
  ContentRadio,
  ContentRadioContainer,
  ContentRadioTypo,
  MenuContainer,
  QuestionContainer,
  QuestionDeleteButton,
  QuestionItemAddButton,
  QuestionItemContainer,
  QuestionItemDeleteButton,
  RadioContainer,
  Root,
  TitleContainer,
} from './styled'

type EditableQuestionCardProps = {
  className?: string
  questionData: QuestionItemType
  onChangeQuestionType: (type: QuestionType) => () => void
  onDeleteQuestion: (e: any) => void
  onChangeQuestionTitle: (e: any) => void
  onChangeQuestionScore: (e: any) => void
  onChangeQuestionAnswer: (e: any) => void
  onChangeOption: (optionKey: number) => (e: any) => void
  onCreateOption: () => void
  onDeleteOption: (optionKey: number) => () => void
}

export const EditableQuestionCard: FC<EditableQuestionCardProps> = ({
  className,
  questionData,
  onChangeQuestionType,
  onChangeQuestionTitle,
  onChangeQuestionAnswer,
  onChangeOption,
  onChangeQuestionScore,
  onCreateOption,
  onDeleteOption,
  onDeleteQuestion,
}) => {
  return (
    <Root className={className}>
      <MenuContainer>
        <RadioContainer>
          <ContentRadioContainer onClick={onChangeQuestionType('SELECT')}>
            <ContentRadio name={'question_type'} checked={questionData.type === 'SELECT'} />
            <ContentRadioTypo>다중 선택</ContentRadioTypo>
          </ContentRadioContainer>
          <ContentRadioContainer onClick={onChangeQuestionType('RADIO')}>
            <ContentRadio name={'question_type'} checked={questionData.type === 'RADIO'} />
            <ContentRadioTypo>단일 선택</ContentRadioTypo>
          </ContentRadioContainer>
        </RadioContainer>
        <QuestionDeleteButton onClick={onDeleteQuestion}>질문 삭제</QuestionDeleteButton>
      </MenuContainer>
      <TitleContainer>
        <ContentInput onChange={onChangeQuestionTitle} addonBefore={'제목'} value={questionData.title} />
      </TitleContainer>
      <AnswerContainer>
        <ContentInput onChange={onChangeQuestionAnswer} addonBefore={'정답'} value={questionData.answer} />
        <ContentInput
          onChange={onChangeQuestionScore}
          type={'number'}
          addonBefore={'배점'}
          addonAfter={'점'}
          value={questionData.score}
        />
      </AnswerContainer>
      <QuestionContainer>
        {questionData.optionList.map((optionItem) => (
          <QuestionItemContainer key={`option_${optionItem.key}`}>
            <ContentInput
              onChange={onChangeOption(optionItem.key)}
              addonBefore={`선택지 ${optionItem.key}`}
              value={optionItem.title}
            />
            <QuestionItemDeleteButton onClick={onDeleteOption(optionItem.key)}>선택지 삭제</QuestionItemDeleteButton>
          </QuestionItemContainer>
        ))}
        <QuestionItemAddButton onClick={onCreateOption}>선택지 추가</QuestionItemAddButton>
      </QuestionContainer>
    </Root>
  )
}
