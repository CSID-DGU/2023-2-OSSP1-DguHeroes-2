import { MOBILE_MAX_WIDTH } from 'constants/system/layout'
import styled from 'styled-components'

type RootProps = {
  on: 'MOBILE' | 'DESKTOP'
}

export const Root = styled.div<RootProps>`
  ${(props) => props.on === 'MOBILE' && `display: none;`}
  @media screen and (max-width: ${MOBILE_MAX_WIDTH}px) {
    ${(props) => props.on === 'DESKTOP' && `display: none;`}
    ${(props) => props.on === 'MOBILE' && `display: unset;`}
  }
`
