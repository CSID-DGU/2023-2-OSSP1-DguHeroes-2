// import { CommonHeader } from 'components/CommonHeader'
import { FC, useState } from 'react'
import { InputTitleRequired, ReactQuillStyled, Root } from './styled'
import 'react-quill/dist/quill.snow.css'
import { useNavigate } from 'react-router-dom'
// import { camelizeKey } from 'utils/camelizeKey'

type ChangeProjectSectionProps = {
  className?: string
}
// 여기에 들어갈 json 데이터 정의 필요!
const modules = {
  toolbar: {
    container: [
      [{ header: [1, 2, 3, 4, 5, 6, false] }],
      [{ font: [] }],
      [{ align: [] }],
      ['bold', 'italic', 'underline', 'strike', 'blockquote'],
      [{ list: 'ordered' }, { list: 'bullet' }, 'link'],
      [
        {
          color: [
            '#000000',
            '#e60000',
            '#ff9900',
            '#ffff00',
            '#008a00',
            '#0066cc',
            '#9933ff',
            '#ffffff',
            '#facccc',
            '#ffebcc',
            '#ffffcc',
            '#cce8cc',
            '#cce0f5',
            '#ebd6ff',
            '#bbbbbb',
            '#f06666',
            '#ffc266',
            '#ffff66',
            '#66b966',
            '#66a3e0',
            '#c285ff',
            '#888888',
            '#a10000',
            '#b26b00',
            '#b2b200',
            '#006100',
            '#0047b2',
            '#6b24b2',
            '#444444',
            '#5c0000',
            '#663d00',
            '#666600',
            '#003700',
            '#002966',
            '#3d1466',
            'custom-color',
          ],
        },
        { background: [] },
      ],
      ['image', 'video'],
      ['clean'],
    ],
  },
}
export const ChangeProjectSection: FC<ChangeProjectSectionProps> = ({ className }) => {
  const navigate = useNavigate()
  // eslint-disable-next-line no-unused-vars
  const [projectContent, setProjectContent] = useState<string>()
  const onChangeContents = (contents: any) => {
    setProjectContent(contents);
  }
  const onClickChangeProject = () => {
    navigate('/')
    // eslint-disable-next-line no-undef
    alert("프로젝트 수정이 완료되었습니다.")
  }
  
  return (
    <Root className={className}>
      <InputTitleRequired type="primary" onClick={onClickChangeProject}>수정하기</InputTitleRequired>
      <ReactQuillStyled modules={modules} onChange={onChangeContents}/>
    </Root>
  )
}
