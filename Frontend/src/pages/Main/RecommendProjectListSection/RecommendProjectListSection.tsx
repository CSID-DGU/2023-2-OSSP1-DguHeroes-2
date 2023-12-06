import projectListIcon2Img from 'assets/images/main/project_list_icon2.png'
import { ProjectCard } from 'components/ProjectCard'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC, useState} from 'react'
import { ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { Container, ProjectCardContainer, Root, TitleContainer, TitleLogoImg, LeftContainer, RightContainer, TitleTypo } from './styled'

import { stacks } from 'types/stacks'; // 전체 기술 스택 옵션
import { Select } from 'antd'

type RecommendProjectListSectionProps = {
  className?: string
  recommendedProjectList: ProjectListType
}
// style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}
export const RecommendProjectListSection: FC<RecommendProjectListSectionProps> = ({ className, recommendedProjectList }) => {

  const [selectedStack, setselectedStack] = useState<string>() // 사용자가 선택한 스택

  const projectList = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  return (
    <Root className={className}>
      <Container>
        <TitleContainer >

          <LeftContainer>
          <TitleLogoImg src={projectListIcon2Img} alt={'추천 프로젝트 로고 이미지'} />
          <TitleTypo>추천 프로젝트</TitleTypo>
          </LeftContainer>

          <RightContainer>
          <Select onChange={(value) => setselectedStack(value)} options={stacks} size="large" placeholder="추천 받을 프로젝트의 기술 스택" style={{ width: 500 }}/>  
          </RightContainer>
          
        </TitleContainer>
        <ProjectCardContainer>
          {projectList.slice(0, 4).map((projectItem) => (
            <ProjectCard projectItem={projectItem} key={`project_card_${projectItem.key}`} />
          ))}
        </ProjectCardContainer>
      </Container>
    </Root>
  )
}
