import { Button, Tag, Typography } from 'antd'
import { CONTAINER_WIDTH, HEADER_HEIGHT } from 'constants/system/layout'
import styled from 'styled-components'

const SIDE_SECTION_WIDTH = 300

export const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: ${HEADER_HEIGHT}px;
  position: relative;
  padding-bottom: 80px;
`

export const Container = styled.div`
  width: ${CONTAINER_WIDTH}px;
  display: flex;
  gap: 20px;
  margin-top: 40px;
`

export const MainContainer = styled.div`
  width: ${CONTAINER_WIDTH - SIDE_SECTION_WIDTH - 20}px;
  display: flex;
  flex-direction: column;
  gap: 10px;
`

export const BannerImg = styled.img`
  width: 100%;
  height: 300px;
  object-fit: cover;
`

export const TitleTypo = styled(Typography)`
  font-size: 24px;
  font-weight: 500;
`

export const DevelopmentStackTagContainer = styled.div`
  display: flex;
`

export const DevelopmentStackTag = styled(Tag)``

export const MenuContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px #c9c9c9 solid;
`

export const MenuLeaderUserInfoContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`

export const MenuLeaderUserInfoProfileImg = styled.img`
  width: 30px;
  height: 30px;
  border-radius: 30px;
`

export const MenuLeaderUserInfoNameTypo = styled(Typography)``

export const MenuCountContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`

export const MenuCountVisitedTypo = styled(Typography)``

export const MenuCountLikeTypo = styled(Typography)``

export const MenuCountDivider = styled.span`
  width: 1px;
  height: 15px;
  background: #c9c9c9;
`

export const ContentContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px 0;
`

export const ContentTitleTypo = styled(Typography)`
  font-size: 18px;
  line-height: 180%;
`
export const ContentTypo = styled(Typography)``
export const ContentUl = styled.ul`
  margin-bottom: 20px;
`
export const ContentLi = styled.li``

export const SideSectionContainer = styled.div`
  width: ${SIDE_SECTION_WIDTH}px;
  height: fit-content;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px 20px;
  box-sizing: border-box;
  border: 1px #c9c9c9 solid;
  border-top: 4px #00f4 solid;
`

export const SideSectionProfileImgWrapper = styled.div`
  width: 100%;
  border-bottom: 1px #c9c9c9 solid;
`

export const SideSectionProfileImg = styled.img`
  width: 100%;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 20px;
`

export const SideSectionRequireMemberContainer = styled.div`
  width: 100%;
  margin-top: 15px;
`

export const SideSectionRequireMemberTitleTypo = styled(Typography)`
  font-size: 16px;
  font-weight: 700;
`

export const SideSectionRequireMemberContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
  padding-bottom: 15px;
  border-bottom: 1px #c9c9c9 solid;
  margin-top: 10px;
`

export const SideSectionRequireMemberItemContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`

export const SideSectionRequireMemberItemTypo = styled(Typography)`
  font-size: 14px;
  font-weight: 500;
  color: #333;
`

export const SideSectionDateRangeContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px #c9c9c9 solid;
`

export const SideSectionDateRangeTitleTypo = styled(Typography)`
  font-size: 16px;
  font-weight: 700;
`

export const SideSectionDateRangeTypo = styled(Typography)`
  font-size: 12px;
  font-weight: 500;
  color: #333;
`

export const SideSectionProjectTypeContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px #c9c9c9 solid;
`

export const SideSectionProjectTypeTitleTypo = styled(Typography)`
  font-size: 16px;
  font-weight: 700;
`

export const SideSectionProjectTypeTypo = styled(Typography)`
  font-size: 14px;
  font-weight: 700;
  color: #333;
`
export const SideSectionManageProjectButton = styled(Button)`
  width: 100%;
  color: white;
  background: #3cb371;
  margin-top: 20px;
`
export const SideSectionApplyProjectButton = styled(Button)`
  width: 100%;
  background: #00f8;
  margin-top: 20px;
`

export const SideSectionQuitProjectButton = styled(Button)`
  width: 100%;
  color: white;
  background: #BDBDBD;
  margin-top: 20px;
`

export const SideSectionInvitedProjectButtonContainer = styled.div`
  display: flex;
  width: 100%;
  gap: 10px;
`

export const LikeButton = styled.div`
  color:#A9D0F5;
`
/*
export const SideSectionInvitedProjectApplyButton = styled(Button)`
  display: flex;
  gap: 10px;
`
*/
