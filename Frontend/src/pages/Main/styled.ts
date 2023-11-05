import { HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding-top: ${HEADER_HEIGHT}px;
  position: relative;
  padding-bottom: 80px;
`
