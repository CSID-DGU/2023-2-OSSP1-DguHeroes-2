import { Button, Input, Typography } from 'antd'
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
  align-items: center;
  gap: 10px;
  margin-top: 60px;
`

export const ContentInput = styled(Input)`
  width: 100%;
`

export const LoginButton = styled(Button)`
  width: 100%;
`

export const JoinContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 10px;
  align-items: center;
  padding-top: 15px;
  border-top: 1px #c9c9c9 solid;
  margin-top: 15px;
`

export const JoinInfoTypo = styled(Typography)``

export const JoinButton = styled(Button)``
