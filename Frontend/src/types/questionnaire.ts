type OptionItemType = {
  key: number
  title: string
}

type OptionListType = OptionItemType[]

export type QuestionType = 'RADIO' | 'SELECT'

export type QuestionItemType = {
  key: number
  type: QuestionType
  title: string
  answer: string
  score: number
  optionList: OptionListType
}

export type QuestionListType = QuestionItemType[]

export type QuestionnaireItemType = {
  key: number
  title: string
  version: number
  questionnaireType: string
  questionList: QuestionListType
}

export type QuestionnaireListType = {
  questionnaireList: QuestionnaireItemType[]
}

export type QuestionAnswerSheetType = {
  questionKey: number
  answer: number[]
}

export type QuestionAnswerSheetListType = QuestionAnswerSheetType[]

export type QuestionnaireAnswerSheetType = {
  questionnaireKey: number
  questionAnswerSheetList: QuestionAnswerSheetType[]
}
