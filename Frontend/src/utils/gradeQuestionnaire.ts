import { QuestionAnswerSheetListType, QuestionnaireItemType } from 'types/questionnaire'

const compareNumberArrayAnswerAndStringAnswer = (numberArrayAnswer: number[], stringAnswer: string) => {
  let washedStringAnswer = stringAnswer.replaceAll(' ', '')
  let washedNumberArrayAnswer = numberArrayAnswer
    .sort()
    .toString()
    .replaceAll(' ', '')
    .replace('[', '')
    .replace(']', '')
  return washedNumberArrayAnswer === washedStringAnswer
}

export const gradeQuestionnaire = (
  questionnaireItem: QuestionnaireItemType,
  answerSheetData: QuestionAnswerSheetListType
) => {
  let grade = 0
  let totalGrade = 0
  let scorePercent = 0
  questionnaireItem.questionList.forEach((questionItem) => {

    totalGrade += questionItem.score
    if (
      compareNumberArrayAnswerAndStringAnswer(
        answerSheetData.filter((value) => value.questionKey === questionItem.key)[0].answer,
        questionItem.answer
      )
    ) {
      grade += +questionItem.score
    }
  })
  scorePercent = Math.ceil((grade / totalGrade) * 100)
  return scorePercent
}
