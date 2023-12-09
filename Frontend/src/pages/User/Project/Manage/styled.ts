import { Button, Typography } from 'antd'
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
export const ManageTitleContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 10px 0;
  gap: 10px;
`

export const ManageTitleLogoImg = styled.img`
  width: 20px;
`

export const ManageTitleTypo = styled(Typography)`
  font-size: 18px;
  font-weight: bold;
`

export const ManageExplainTypo = styled(Typography)`
  width: 100%;
  font-size: 12px;
  font-weight: 400;
  color: #808080;
`
export const ManageExplainContainer = styled(Typography)`
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding-bottom: 10px;
  border-bottom: 1px #c9c9c9 solid;
`
export const ProjectDeleteButton = styled(Button)`
`
