import { Button, Typography } from 'antd'
import { CONTAINER_WIDTH, HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'

export const Root = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  padding-top: ${HEADER_HEIGHT}px;
  padding-bottom: 200px;
`

export const HeaderRoot = styled.div`
  width: 100%;
  background: white;
  display: flex;
  justify-content: center;
  border-bottom: 1.5px #eee solid;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
`

export const HeaderContainer = styled.div`
  width: ${CONTAINER_WIDTH}px;
  height: ${HEADER_HEIGHT}px;
  display: flex;
  align-items: center;
`

export const HeaderTypo = styled(Typography)`
  &&& {
    font-size: 24px;
    font-weight: 500;
  }
`

export const TitleContainer = styled.div`
  display: flex;
  gap: 10px;
`

export const TitleTypo = styled(Typography)`
  font-size: 24px;
  font-weight: 500;
`

export const ContentContainer = styled.div`
  width: 800px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding-top: 20px;
`

export const QuestionnaireCardContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`

export const QuestionnaireCard = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
  border: 1px #c9c9c9 solid;
  border-radius: 8px;
`

export const QuestionCardTitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: 500;
`

export const QuestionCardEditButton = styled(Button)``
