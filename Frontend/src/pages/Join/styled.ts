import { Button, Input, Select, Typography } from 'antd'
import TextArea from 'antd/es/input/TextArea'
import { HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: ${HEADER_HEIGHT}px;
  position: relative;
  padding-bottom: 80px;
`

export const Container = styled.div`
  width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
`

export const LogoImg = styled.img`
  width: 100%;
  margin-top: 60px;
`

export const LogoTypo = styled(Typography)`
  font-size: 14px;
`

export const InputContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 20px;
`

export const ContentInput = styled(Input)``

export const ContentTextArea = styled(TextArea)`
  max-height: 250px;
`

export const ContentSelect = styled(Select)``

export const QuestionnaireButton = styled(Button)``

export const QuestionnaireScoreTypo = styled(Typography)``

export const JoinButton = styled(Button)`
  width: 100%;
  margin-top: 20px;
`
