import { CONTAINER_WIDTH, HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'
import { Tag, Typography } from 'antd'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: ${HEADER_HEIGHT}px;
  position: relative;
  padding-bottom: 80px;
  .ant-list {
    width: 90%;
    background-color: aliceblue;
    border: none;
  }
`

export const Container = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
`
export const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 3px;
`
export const UserIcon = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50%;
  //margin-left: 5px;
  //margin-right: 20px;
  cursor: pointer;
`
export const UserNameTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const StackTag = styled(Tag)`
  background-color: #d9d9d9;
  border: none;
`
