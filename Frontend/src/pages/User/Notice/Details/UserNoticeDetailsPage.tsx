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
  SideSectionInvitedProjectButtonContainer,
} from './styled'

type UserNoticeDetailsPageProps = {
  className?: string
}

export const UserNoticeDetailsPage: FC<UserNoticeDetailsPageProps> = ({ className }) => {
  const navigate = useNavigate()
  const { projectKey = 0 } = useParams()
  const projectListSampleData: ProjectListType = camelizeKey(projectListSampleJson.project_list) as ProjectListType
  const [projectItem, setProjectItem] = useState<ProjectItemType>()

  useEffect(() => {
    setProjectItem(projectListSampleData[+projectKey])
  }, [])

  const onClickAcceptProject = () => {
    navigate('/')
    // eslint-disable-next-line no-undef
    alert("초대가 수락되었습니다.")
  }

  const onClickRejectProject = () => {
    navigate('/')
    // eslint-disable-next-line no-undef
    alert("초대가 거절되었습니다.")
  }
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
            <SideSectionInvitedProjectButtonContainer>
              <SideSectionApplyProjectButton type={'primary'} onClick={onClickAcceptProject}>수락하기</SideSectionApplyProjectButton>
              <SideSectionQuitProjectButton type={'primary'} onClick={onClickRejectProject}>거절하기</SideSectionQuitProjectButton>
            </SideSectionInvitedProjectButtonContainer>
          </SideSectionContainer>
        </Container>
      )}
    </Root>
  )
}