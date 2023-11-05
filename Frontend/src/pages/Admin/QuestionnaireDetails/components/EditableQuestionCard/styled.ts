import { Button, Input, Radio, Typography } from 'antd'
import styled from 'styled-components'

export const Root = styled.div`
  width: 800px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  box-sizing: border-box;
  border: 1px #c9c9c9 solid;
  border-radius: 8px;
`

export const MenuContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`

export const RadioContainer = styled.div`
  width: 100%;
  display: flex;
  gap: 15px;
  cursor: pointer;
`

export const ContentRadioContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 6px;
`

export const QuestionDeleteButton = styled(Button)``

export const ContentRadioTypo = styled(Typography)``

export const ContentRadio = styled(Radio)``

export const ContentInput = styled(Input)``

export const TitleContainer = styled.div``

export const AnswerContainer = styled.div`
  width: 100%;
  display: flex;
  gap: 15px;
`

export const QuestionContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 15px;
`

export const QuestionItemContainer = styled.div`
  width: 100%;
  display: flex;
  gap: 15px;
`

export const QuestionItemDeleteButton = styled(Button)``

export const QuestionItemAddButton = styled(Button)``
