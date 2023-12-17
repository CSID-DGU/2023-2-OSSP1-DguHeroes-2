import projectListIcon2Img from 'assets/images/main/project_list_icon2.png'
import { ProjectCard } from 'components/ProjectCard'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC } from 'react'
import { ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { Container, ProjectCardContainer, Root, TitleContainer, TitleLogoImg, TitleTypo } from './styled'

type RecentProjectListSectionProps = {
  className?: string
  recentProjectList: ProjectListType
}

export const RecentProjectListSection: FC<RecentProjectListSectionProps> = ({ className, recentProjectList }) => {
  const projectList_test = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  const projectList = recentProjectList

  return (
    <Root className={className}>
      <Container>
        <TitleContainer>
          <TitleLogoImg src={projectListIcon2Img} alt={'요즘 뜨는 프로젝트 로고 이미지'} />
          <TitleTypo>최근 올라온 프로젝트</TitleTypo>
        </TitleContainer>
        <ProjectCardContainer>
          {projectList_test.slice(0, 4).map((projectItem) => (
            <ProjectCard projectItem={projectItem} key={`project_card_${projectItem.key}`} />
          ))}
        </ProjectCardContainer>
      </Container>
    </Root>
  )
}
