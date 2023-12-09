import { Input, Select, Typography } from 'antd'
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

export const ProjectCardContainer = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 40px;
`
export const Container = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  gap: 20px;
  margin-top: 20px;
`
export const SelectContainer = styled.div`
  width: 370px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 20px;
`

export const SearchContainer = styled.div`
  width: 370px;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
`

export const SearchBox = styled(Input)`
`
export const SelectBox = styled(Select)`
`
export const TitleTypo = styled(Typography)`
  font-size: 15px;
  font-weight: bold;
`