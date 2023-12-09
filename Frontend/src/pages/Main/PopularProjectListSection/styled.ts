import { Typography } from 'antd'
import { CONTAINER_WIDTH } from 'constants/system/layout'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  background: #c9c9c933;
  display: flex;
  justify-content: center;
`

export const Container = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 30px 0;
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

export const ProjectCardContainer = styled.div`
  display: flex;
  gap: 15px;
  justify-content: center;
`
