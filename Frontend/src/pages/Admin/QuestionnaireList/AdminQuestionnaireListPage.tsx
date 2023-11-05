import questionnaireListSampleJson from 'constants/json/questionnaire_list_sample.json'
import { FC, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { QuestionnaireItemType, QuestionnaireListType } from 'types/questionnaire'
import { camelizeKey } from 'utils/camelizeKey'
import {
  ContentContainer,
  HeaderContainer,
  HeaderRoot,
  HeaderTypo,
  QuestionCardEditButton,
  QuestionCardTitleTypo,
  QuestionnaireCard,
  QuestionnaireCardContainer,
  Root,
} from './styled'

type AdminQuestionnaireListPageProps = {
  className?: string
}

export const AdminQuestionnaireListPage: FC<AdminQuestionnaireListPageProps> = ({ className }) => {
  const [questionnaireListData, setQuestionnaireListData] = useState<QuestionnaireListType>()
  const navigate = useNavigate()
  const onClickEditButton = (questionnaireKey: number) => () => {
    navigate(`/admin/questionnaire/${questionnaireKey}`)
  }

  useEffect(() => {
    // eslint-disable-next-line no-undef
    const localStorageQuestionnaireListData = localStorage.getItem('questionnaire_list_sample')
    if (localStorageQuestionnaireListData) {
      setQuestionnaireListData(camelizeKey(JSON.parse(localStorageQuestionnaireListData)) as QuestionnaireListType)
      return
    }
    setQuestionnaireListData(camelizeKey(questionnaireListSampleJson) as QuestionnaireListType)
    // eslint-disable-next-line no-undef
    localStorage.setItem('questionnaire_list_sample', JSON.stringify(questionnaireListSampleJson))
  }, [])

  return (
    <Root className={className}>
      <HeaderRoot>
        <HeaderContainer>
          <HeaderTypo>질문지 어드민_질문지 리스트</HeaderTypo>
        </HeaderContainer>
      </HeaderRoot>
      <ContentContainer>
        <QuestionnaireCardContainer>
          {questionnaireListData?.questionnaireList.map((questionnaireItem: QuestionnaireItemType) => (
            <QuestionnaireCard key={`question_card_${questionnaireItem.key}`}>
              <QuestionCardTitleTypo>
                {questionnaireItem.title} (version: {questionnaireItem.version})
              </QuestionCardTitleTypo>
              <QuestionCardEditButton onClick={onClickEditButton(questionnaireItem.key)}>
                수정하기
              </QuestionCardEditButton>
            </QuestionnaireCard>
          ))}
        </QuestionnaireCardContainer>
      </ContentContainer>
    </Root>
  )
}
