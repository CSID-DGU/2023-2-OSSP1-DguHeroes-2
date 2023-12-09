import { Typography } from 'antd'
import { CONTAINER_WIDTH, HEADER_HEIGHT } from 'constants/system/layout'
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
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
`
export const ManageContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`

export const ManageTitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const ManageProjectContainer = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
  `

export const LikeContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`

export const LikeTitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const LikeCardContainer = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
`

export const ApplyContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`

export const ApplyTitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const ApplyCardContainer = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
`
export const ExpireContainer = styled.div`
width: 100%;
display: flex;
flex-direction: column;
`

export const ExpireTitleTypo = styled(Typography)`
font-size: 18px;
font-weight: bold;
`

export const ExpireProjectCardContainer = styled.div`
width: 100%;
display: flex;
flex-wrap: wrap;
gap: 10px;
margin-top: 20px;
`