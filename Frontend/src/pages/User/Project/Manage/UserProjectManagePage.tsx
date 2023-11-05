import { CommonHeader } from 'components/CommonHeader'
import projectTitleIconImg from 'assets/images/project/titleIcon.png'
import { FC } from 'react'
import { Tabs } from 'antd'
import type { TabsProps } from 'antd'
// import { camelizeKey } from 'utils/camelizeKey'
import {
  Container,
  Root,
  ManageContainer,
  ManageTitleTypo,
  ManageTitleLogoImg,
  ManageTitleContainer,
  ManageExplainTypo,
  ProjectDeleteButton,
  ManageExplainContainer,
} from './styled'
import { SearchMemberSection } from './SearchMemberSection'
import { ApproveMemberSection } from './ApproveMemberSection'
import { ChangeProjectSection } from './ChangeProjectSection'
import { ManageMemberSection } from './ManageMemberSection'
import { useNavigate, useParams } from 'react-router-dom'

type UserProjectManagePageProps = {
  className?: string
}
/*
const onChange = (key: string) => {
  console.log(key);
};
*/

// 여기에 들어갈 json 데이터 정의 필요! + Tabs 컴포넌트 사용하기(antd)
export const UserProjectManagePage: FC<UserProjectManagePageProps> = ({ className }) => {
  const navigate = useNavigate()
  const { projectKey = 0 } = useParams()
  const { projectTitle = "프로젝트 제목" } = useParams()
  
  const items: TabsProps['items'] = [
    {
      key: '1',
      label: `참여멤버 관리`,
      children: <ManageMemberSection />,
    },
    {
      key: '2',
      label: `추천멤버 조회`,
      children: <SearchMemberSection projectKey={projectKey}/>,
    },
    {
      key: '3',
      label: `멤버 지원승인`,
      children: <ApproveMemberSection />,
    },
    {
      key: '4',
      label: `게시글 수정`,
      children: <ChangeProjectSection />,
    }
  ]

  const onClickProjectDelete = () => {
    navigate('/')
    // eslint-disable-next-line no-undef
    alert("프로젝트가 삭제되었습니다.")
  }
  // const manageProjectListData = camelizeKey(manageProjectListSampleJson.project_list) as ManageProjectListType
  // const expireProjectListData = camelizeKey(expireProjectListSampleJson.project_list) as ExpireProjectListType
  return (
    <Root className={className}>
      <CommonHeader />
      <Container>
        <ManageContainer>
          <ManageTitleContainer>
            <ManageTitleLogoImg src={projectTitleIconImg} alt={'요즘 뜨는 프로젝트 로고 이미지'} />
            <ManageTitleTypo>{projectTitle}</ManageTitleTypo>
          </ManageTitleContainer>
          <ManageExplainContainer>
            <ManageExplainTypo>프로젝트 관리 페이지 입니다.</ManageExplainTypo>
            <ProjectDeleteButton onClick={onClickProjectDelete}>프로젝트 삭제</ProjectDeleteButton>
          </ManageExplainContainer>
          <Tabs defaultActiveKey="1" items={items} />
        </ManageContainer>
      </Container>
    </Root>
  )
}
