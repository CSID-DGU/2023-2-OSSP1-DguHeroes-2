import { Button, Popover, Typography } from 'antd'
import { CONTAINER_WIDTH, HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'
import { QuestionCircleOutlined } from '@ant-design/icons';

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
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
`
export const TitleContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`

export const TitleLogoImg = styled.img`
  width: 20px;
`

export const TitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const ProjectOptionContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 10px;
  padding: 20px 10px;
  border: 1px #c9c9c9 solid;
  border-radius: 5px;
`

export const ProjectOptionLeftContainer = styled.div`
  width: 40%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10px;
  padding-right: 10px;
  border-right: 1px #c9c9c9 solid;
`

export const ProjectOptionRightContainer = styled.div`
  width: 60%;
  display: flex;
  flex-direction: column;
  //align-items: center;
  // gap: 10px; // 지울까?
  margin: 10px;
  //padding-left: 10px;
`
export const LeaderPositionContainer = styled.div`
  width: 20%;
  margin-right: 30px;
`
export const InputTitleRequired = styled(Typography)`
  padding-bottom: 10px;
  font-size: 15px;
  font-weight: bold;
  &::after {
    padding-left: 5px;
    content: '*';
    color: red;
  }
`

export const ProjectMemberInputTitleContainer = styled.div`
  display: flex;
  gap: 10px;
`
export const ProjectMemberExplainWrapper = styled(Popover)`
`

export const ProjectMemberExplainContentWrapper = styled.div`
  min-width: 500px;
  padding: 20px 10px;
`

export const ProjectMemberExplainTitle = styled(Typography)`
  padding-bottom: 10px;
  font-size: 25px;
  font-weight: bold;
`

export const ProjectMemberExplainText = styled(Typography)`
  padding-bottom: 10px;
  font-size: 15px;
`

export const ProjectMemberExplainIcon = styled(QuestionCircleOutlined)`
  margin-top: 5px;
`

export const InputTitle = styled(Typography)`
  padding-bottom: 10px;
  font-size: 15px;
  font-weight: bold;
`

export const InputContainer = styled.div`
  width: 100%;
  display: flex;
  flex-flow: row wrap;
`

export const InputTitleContainer = styled.div`
  width: 50%;
  margin-bottom: 20px;
`

export const SearchContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`
export const LocationContainer = styled.div`
  display: flex;
  flex-direction: column;
`
export const ProjectCreateButton = styled(Button)``
export const ProjectMemberInputContainer = styled.div``
export const ProjectDateContainer = styled.div``