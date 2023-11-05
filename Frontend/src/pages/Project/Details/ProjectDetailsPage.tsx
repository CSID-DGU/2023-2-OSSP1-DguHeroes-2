import defaultBackgroundImg from 'assets/images/default_background.png'
import missingAvatarImg from 'assets/images/missing_avatar.png'
import { CommonHeader } from 'components/CommonHeader'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { ProjectItemType, ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { generateRandomProjectCardLogoImg } from 'utils/generateRandomProjectCardLogoImg'
import { getDevelopmentStackColor, translateDevelopmentStack } from 'utils/translateDevelopmentStack'
import {HeartOutlined, HeartFilled} from '@ant-design/icons'
// import { translateProjectPosition } from 'utils/translatePosition'
import {
  BannerImg,
  Container,
  ContentContainer,
  ContentLi,
  ContentTitleTypo,
  ContentTypo,
  ContentUl,
  DevelopmentStackTag,
  DevelopmentStackTagContainer,
  MainContainer,
  MenuContainer,
  MenuCountContainer,
  MenuCountDivider,
  MenuCountLikeTypo,
  MenuCountVisitedTypo,
  MenuLeaderUserInfoContainer,
  MenuLeaderUserInfoNameTypo,
  MenuLeaderUserInfoProfileImg,
  Root,
  SideSectionManageProjectButton,
  SideSectionApplyProjectButton,
  SideSectionQuitProjectButton,
  SideSectionContainer,
  SideSectionDateRangeContainer,
  SideSectionDateRangeTitleTypo,
  SideSectionDateRangeTypo,
  SideSectionProfileImg,
  SideSectionProfileImgWrapper,
  SideSectionProjectTypeContainer,
  SideSectionProjectTypeTitleTypo,
  SideSectionProjectTypeTypo,
  SideSectionRequireMemberContainer,
  SideSectionRequireMemberContentContainer,
  SideSectionRequireMemberItemContainer,
  SideSectionRequireMemberItemTypo,
  SideSectionRequireMemberTitleTypo,
  TitleTypo,
  LikeButton,
} from './styled'
import { GetProjectDetailsRequestType, GetProjectDetailsResponseType, getProjectDetails } from 'api/getProjectDetails'

type ProjectDetailsPageProps = {
  className?: string
}

export const ProjectDetailsPage: FC<ProjectDetailsPageProps> = ({ className }) => {
  const navigate = useNavigate()
  
  const { projectKey = 0 } = useParams()
  const projectListSampleData: ProjectListType = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  const [projectItem, setProjectItem] = useState<ProjectItemType>()

  const [liked, setLiked] = useState(false);

  const handleLikeToggle = () => {
    setLiked((prevLiked) => !prevLiked);
  }

  const onClickProjectManage = (projectTitle: string) => {
    navigate(`/user/project/manage/${projectKey}/${projectTitle}`)
  }

  const onClickApplyProject = () => {
    navigate('/')
    // eslint-disable-next-line no-undef
    alert("프로젝트에 지원 완료되었습니다.")
  }
  
  const renderButton = (ProjectItem: ProjectItemType) => {
    if (ProjectItem.valid === 'VALID') {
      if (ProjectItem.position === 'LEADER') {
        return <SideSectionManageProjectButton type={'ghost'} onClick={() => onClickProjectManage(ProjectItem.title)}>관리하기</SideSectionManageProjectButton>
      } else if (ProjectItem.position === 'MEMBER') {
        return (
          <SideSectionQuitProjectButton type={'ghost'} disabled>
            참여 중
          </SideSectionQuitProjectButton>
        )
      } else if (ProjectItem.position === 'NORMAL') {
        return <SideSectionApplyProjectButton type={'primary'} onClick={onClickApplyProject}>지원하기</SideSectionApplyProjectButton>
      } 
    } else {
      return (
        <SideSectionQuitProjectButton type={'ghost'} disabled>
          만료됨
        </SideSectionQuitProjectButton>
      )
    }
    return null
  }

  useEffect(() => {
    let data: GetProjectDetailsRequestType = {
      projectKey: 0
    }
    if(projectKey !== 0) {
       data.projectKey = parseInt(projectKey)
    }
    getProjectDetails(data)
    .then((response: GetProjectDetailsResponseType) => {
      if (response.status === 'SUCCESS') {
        // eslint-disable-next-line no-undef
        console.log('SUCCESS');
        // projectDetails 받아서 가공하기
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
    // 이걸 대체하기
    setProjectItem(projectListSampleData[+projectKey])
  }, [])

  return (
    <Root className={className}>
      <CommonHeader />
      <BannerImg src={defaultBackgroundImg} />
      {projectItem && (
        <Container>
          <MainContainer>
            <TitleTypo>{projectItem.title}</TitleTypo>
            <DevelopmentStackTagContainer>
              {projectItem.requireMemberList.map((requireMemberItem, index) => (
                <DevelopmentStackTag
                  color={getDevelopmentStackColor(requireMemberItem.developmentStack)}
                  key={`development_stack_tag_${index}`}
                >
                  {translateDevelopmentStack(requireMemberItem.developmentStack)}
                </DevelopmentStackTag>
              ))}
            </DevelopmentStackTagContainer>
            <MenuContainer>
              <MenuLeaderUserInfoContainer>
                <MenuLeaderUserInfoProfileImg src={missingAvatarImg} />
                <MenuLeaderUserInfoNameTypo>코딩조아</MenuLeaderUserInfoNameTypo>
              </MenuLeaderUserInfoContainer>
              <MenuCountContainer>
                <MenuCountVisitedTypo>조회수: {projectItem.visitedNumber}회</MenuCountVisitedTypo>
                <MenuCountDivider />
                <MenuCountLikeTypo>좋아요: {projectItem.likeNumber}회</MenuCountLikeTypo>
                <LikeButton onClick={handleLikeToggle}>
                  {liked ? <HeartFilled /> : <HeartOutlined />}
                </LikeButton>
              </MenuCountContainer>
            </MenuContainer>
            <ContentContainer>
              <ContentTitleTypo>프로젝트 시작 동기</ContentTitleTypo>
              <ContentUl>
                <ContentLi>
                  <ContentTypo>장소기반으로 모임을 제시하여...</ContentTypo>
                </ContentLi>
                <ContentLi>
                  <ContentTypo>모임을 통해 자신의 관심사를...</ContentTypo>
                </ContentLi>
              </ContentUl>
              <ContentTitleTypo>회의 진행/모임 방식</ContentTitleTypo>
              <ContentUl>
                <ContentLi>
                  <ContentTypo>장소기반으로 모임을 제시하여...</ContentTypo>
                </ContentLi>
                <ContentLi>
                  <ContentTypo>모임을 통해 자신의 관심사를...</ContentTypo>
                </ContentLi>
              </ContentUl>
              <ContentTitleTypo>그 외 자유기재</ContentTitleTypo>
              <ContentUl>
                <ContentLi>
                  <ContentTypo>장소기반으로 모임을 제시하여...</ContentTypo>
                </ContentLi>
                <ContentLi>
                  <ContentTypo>모임을 통해 자신의 관심사를...</ContentTypo>
                </ContentLi>
              </ContentUl>
            </ContentContainer>
          </MainContainer>
          <SideSectionContainer>
            <SideSectionProfileImgWrapper>
              <SideSectionProfileImg
                src={projectItem.representativeImg ?? generateRandomProjectCardLogoImg(projectItem.key)}
                alt={'프로젝트 대표 이미지'}
              />
            </SideSectionProfileImgWrapper>
            <SideSectionRequireMemberContainer>
              <SideSectionRequireMemberTitleTypo>모집 인원</SideSectionRequireMemberTitleTypo>
              <SideSectionRequireMemberContentContainer>
                {projectItem.requireMemberList.map((requireMemberItem, index) => (
                  <SideSectionRequireMemberItemContainer key={`side_section_require_member_item_${index}`}>
                    <SideSectionRequireMemberItemTypo>
                      {translateDevelopmentStack(requireMemberItem.developmentStack)}
                    </SideSectionRequireMemberItemTypo>
                    <SideSectionRequireMemberItemTypo>0/{requireMemberItem.number}</SideSectionRequireMemberItemTypo>
                  </SideSectionRequireMemberItemContainer>
                ))}
              </SideSectionRequireMemberContentContainer>
            </SideSectionRequireMemberContainer>
            <SideSectionDateRangeContainer>
              <SideSectionDateRangeTitleTypo>프로젝트 기간</SideSectionDateRangeTitleTypo>
              <SideSectionDateRangeTypo>2023.05.15 ~ 2023.06.01</SideSectionDateRangeTypo>
            </SideSectionDateRangeContainer>
            <SideSectionProjectTypeContainer>
              <SideSectionProjectTypeTitleTypo>분야</SideSectionProjectTypeTitleTypo>
              <SideSectionProjectTypeTypo>{projectItem.projectType}</SideSectionProjectTypeTypo>
            </SideSectionProjectTypeContainer>
            {renderButton(projectItem)}
          </SideSectionContainer>
        </Container>
      )}
    </Root>
  )
}