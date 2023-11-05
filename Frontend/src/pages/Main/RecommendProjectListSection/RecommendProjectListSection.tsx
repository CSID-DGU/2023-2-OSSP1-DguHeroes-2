import projectListIcon2Img from 'assets/images/main/project_list_icon2.png'
import { ProjectCard } from 'components/ProjectCard'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC } from 'react'
import { ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { Container, ProjectCardContainer, Root, TitleContainer, TitleLogoImg, TitleTypo } from './styled'

type RecommendProjectListSectionProps = {
  className?: string
  recommendedProjectList: ProjectListType
}

export const RecommendProjectListSection: FC<RecommendProjectListSectionProps> = ({ className, recommendedProjectList }) => {
  const projectList = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  return (
    <Root className={className}>
      <Container>
        <TitleContainer>
          <TitleLogoImg src={projectListIcon2Img} alt={'추천 프로젝트 로고 이미지'} />
          <TitleTypo>추천 프로젝트</TitleTypo>
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
