import { Typography, Space, Tag } from 'antd'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  padding-bottom: 20px;
  border: 1px #c9c9c955 solid;
  background: #c9c9c933;
`
export const Container = styled.div`
  width: 1130px;
  display: flex;
  border: 1px #c9c9c955 solid;
  border-radius: 16px;
  margin-right: 15px;
  background: white;
  position: relative;
`
export const UserContainer = styled.div`
  display: flex;
`
export const UserIcon = styled.img`
  width: 150px;
  height: 150px;
  border-radius: 50%;
  margin: 30px;
`
export const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 30px;
`

export const UserNicknameTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`
export const UserIntroductionTypo = styled(Typography)`
  color: #303030;
  margin-top: 10px;
  margin-left: 10px;
  font-size: 14px;
`
export const DevelopmentStackTagContainer = styled(Space)`
  height: 50px;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
`
export const ModifyButton = styled(Tag)`
  position: absolute;
  top: 15px;
  right: 10px;
  z-index: 1;
`
export const DevelopmentStackTag = styled(Tag)``