import { CONTAINER_WIDTH } from 'constants/system/layout'
import styled from 'styled-components'
import ReactQuill from 'react-quill'
import { Button } from 'antd'

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: left;
  padding-top: 10px;
  position: relative;
  padding-bottom: 80px;
`

export const Container = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 10px;
`

export const InputTitleRequired = styled(Button)`
  width: 100px;
  padding-left: 10px;
  margin-bottom: 10px;
  font-size: 15px;
  font-weight: bold;
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

