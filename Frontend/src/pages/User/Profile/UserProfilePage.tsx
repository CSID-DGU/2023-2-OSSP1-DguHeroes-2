import { CommonHeader } from 'components/CommonHeader'
import { ManageProjectCard } from 'components/ManageProjectCard'
import { ProjectCard } from 'components/ProjectCard'
import applyProjectListSampleJson from 'constants/json/apply_project_list_sample.json'
//import manageProjectListSampleJson from 'constants/json/manage_project_list_sample.json'
//import expireProjectListSampleJson from 'constants/json/expire_project_list_sample.json'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC, useEffect } from 'react'
import { ApplyProjectListType, ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import {
  ManageProjectContainer,
  ManageContainer,
  ManageTitleTypo,
  ApplyCardContainer,
  ApplyContainer,
  ApplyTitleTypo,
  Container,
  LikeCardContainer,
  LikeContainer,
  LikeTitleTypo,
  ExpireProjectCardContainer,
  ExpireContainer,
  ExpireTitleTypo,
  Root,
} from './styled'
import { ProfileHeader } from './profileHeader'
import { GetUserprojectListResponseType, getUserprojectList } from 'api/getUserprojectList'

type UserProfilePageProps = {
  className?: string
}

export const UserProfilePage: FC<UserProfilePageProps> = ({ className }) => {
  useEffect(() => {
    getUserprojectList()
    .then((response: GetUserprojectListResponseType) => {
      if (response.status === 'SUCCESS') {
        // eslint-disable-next-line no-undef
        console.log('SUCCESS');
        // 데이터 가져오기
      } else {
        // eslint-disable-next-line no-undef
        console.log('FAIL');
        // eslint-disable-next-line no-undef
        console.log('Error message:', response.message);
      }
    })
    .catch((error: any) => {
      // eslint-disable-next-line no-undef
      console.error('Error :', error);
    });
  }, [])



  const projectListData = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  const applyProjectListData = camelizeKey(applyProjectListSampleJson.project_list) as ApplyProjectListType
  const MyProjectListData = projectListData.filter(
    (projectItem) =>
    projectItem.position === ('LEADER') || projectItem.position === ('MEMBER')
  )
  const ExpiredProjectListData = projectListData.filter(
    (projectItem) =>
    projectItem.valid === ('EXPIRED')
  )
  return (
    <Root className={className}>
      <ProfileHeader />
      <CommonHeader />
      <Container>
        <ManageContainer>
          <ManageTitleTypo>진행중인 프로젝트</ManageTitleTypo>
          <ManageProjectContainer>
            {MyProjectListData.map((projectItem) => (
              <ManageProjectCard
                projectItem={projectItem}
                position={projectItem.position}
                key={`project_card_${projectItem.key}`}
              />
            ))}
          </ManageProjectContainer>
        </ManageContainer>
        <ApplyContainer>
          <ApplyTitleTypo>지원한 프로젝트 현황</ApplyTitleTypo>
          <ApplyCardContainer>
            {applyProjectListData.map((projectItem) => (
              <ProjectCard
                projectItem={projectItem}
                status={projectItem.status}
                key={`project_card_${projectItem.key}`}
              />
            ))}
          </ApplyCardContainer>
        </ApplyContainer>
        <LikeContainer>
          <LikeTitleTypo>내가 좋아요 표시한 프로젝트</LikeTitleTypo>
          <LikeCardContainer>
            {projectListData
              .sort((a, b) => a.key - b.key)
              .slice(0, 4)
              .map((projectItem) => (
                <ProjectCard projectItem={projectItem} key={`project_card_${projectItem.key}`} />
              ))}
          </LikeCardContainer>
        </LikeContainer>
        <ExpireContainer>
          <ExpireTitleTypo>마감된 프로젝트</ExpireTitleTypo>
          <ExpireProjectCardContainer>
            {ExpiredProjectListData.map((projectItem) => (
              <ManageProjectCard
                projectItem={projectItem}
                key={`project_card_${projectItem.key}`}
              />
            ))}
          </ExpireProjectCardContainer>
        </ExpireContainer>
      </Container>
    </Root>
  )
}
