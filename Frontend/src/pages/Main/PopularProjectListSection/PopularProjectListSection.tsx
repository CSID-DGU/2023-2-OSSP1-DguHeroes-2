import projectListIcon1Img from 'assets/images/main/project_list_icon1.png'
import { ProjectCard } from 'components/ProjectCard'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC } from 'react'
import { ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { Container, ProjectCardContainer, Root, TitleContainer, TitleLogoImg, TitleTypo } from './styled'

type PopularProjectListSectionProps = {
  className?: string
  popularProjectList: ProjectListType
}

export const PopularProjectListSection: FC<PopularProjectListSectionProps> = ({ className, popularProjectList }) => {
  const projectList = popularProjectList
  const projectList_test = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  return (
    <Root className={className}>
      <Container>
        <TitleContainer>
          <TitleLogoImg src={projectListIcon1Img} alt={'요즘 뜨는 프로젝트 로고 이미지'} />
          <TitleTypo>요즘 뜨는 프로젝트</TitleTypo>
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
