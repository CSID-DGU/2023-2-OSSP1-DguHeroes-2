import { Checkbox, Radio, Typography } from 'antd'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`

export const TitleContainer = styled.div`
  display: flex;
  align-items: flex-end;
  gap: 5px;
`

export const TitleTypo = styled(Typography)`
  font-size: 16px;
  font-weight: 500;
`

export const ScoreTypo = styled(Typography)`
  font-size: 12px;
  margin-bottom: 2px;
`

export const OptionListContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
  margin-top: 5px;
`

export const ContentRadio = styled(Radio)``
export const ContentCheckbox = styled(Checkbox)``

export const OptionContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`

export const OptionTypo = styled(Typography)``
