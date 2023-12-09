import { Typography } from 'antd'
import { HEADER_HEIGHT } from 'constants/system/layout'
import ReactQuill from 'react-quill'
import styled from 'styled-components'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding-top: ${HEADER_HEIGHT}px;
  position: relative;
  padding-bottom: 80px;
`
export const InputTitleRequired = styled(Typography)`
  padding-bottom: 10px;
  font-size: 15px;
  font-weight: bold;
  &::after {
    padding-left: 5px;
    content: "*";
    color: red;
  }
`

export const ReactQuillStyled = styled(ReactQuill)`
  width: 100%;
  & > .ql-toolbar {
    border-radius: 5px 5px 0 0;
    background-color: aliceblue;
  }

  & > .ql-container {
    min-height: 300px;
    border-radius: 0 0 5px 5px;
  }
`

