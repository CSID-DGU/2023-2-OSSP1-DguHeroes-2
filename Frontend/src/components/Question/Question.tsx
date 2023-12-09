import { FC } from 'react'
import { QuestionItemType } from 'types/questionnaire'
import {
  ContentCheckbox,
  ContentRadio,
  OptionContainer,
  OptionListContainer,
  OptionTypo,
  Root,
  ScoreTypo,
  TitleContainer,
  TitleTypo,
} from './styled'

type QuestionProps = {
  className?: string
  questionItem: QuestionItemType
  questionAnswerData: number[]
  // eslint-disable-next-line no-unused-vars
  onChangeQuestionAnswerSheet: (optionKey: number) => () => void
}

export const Question: FC<QuestionProps> = ({
  className,
  questionItem,
  questionAnswerData,
  onChangeQuestionAnswerSheet,
}) => {
  return (
    <Root className={className}>
      <TitleContainer>
        <TitleTypo>{questionItem.title}</TitleTypo>
        <ScoreTypo>({questionItem.score}점)</ScoreTypo>
      </TitleContainer>
      <OptionListContainer>
        {questionItem.optionList.map((optionItem) => (
          <OptionContainer key={`option_item_${optionItem.key}`}>
            {questionItem.type === 'SELECT' && (
              <ContentCheckbox
                onChange={onChangeQuestionAnswerSheet(optionItem.key)}
                checked={questionAnswerData.includes(optionItem.key)}
              />
            )}
            {questionItem.type === 'RADIO' && (
              <ContentRadio
                onChange={onChangeQuestionAnswerSheet(optionItem.key)}
                checked={questionAnswerData.includes(optionItem.key)}
              />
            )}
            <OptionTypo>{optionItem.title}</OptionTypo>
          </OptionContainer>
        ))}
      </OptionListContainer>
    </Root>
  )
}
