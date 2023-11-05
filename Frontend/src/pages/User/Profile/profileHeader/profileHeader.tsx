import Avatar from 'assets/images/missing_avatar.png'
import UserListSampleJson from 'constants/json/user_list_sample.json'
import { FC, useEffect } from 'react'
import { UserInfoListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { translateDevelopmentStack, getDevelopmentStackColor } from 'utils/translateDevelopmentStack'
import { 
  Root, 
  Container, 
  UserNicknameTypo, 
  UserIcon, 
  UserInfo,
  DevelopmentStackTagContainer,
  DevelopmentStackTag,
  UserIntroductionTypo,
  UserContainer
 } from './styled'
import { GetUserInfoResponseType, getUserInfo } from 'api/getUserInfo'

type ProfileHeaderProps = {
  className?: string
}

export const ProfileHeader: FC<ProfileHeaderProps> = ({ className }) => {
  useEffect(() => {
    getUserInfo()
    .then((response: GetUserInfoResponseType) => {
      if (response.status === 'SUCCESS') {
        // eslint-disable-next-line no-undef
        console.log('SUCCESS');
        // 값 받아오기
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
  
  const UserListData: UserInfoListType = camelizeKey(UserListSampleJson.user_list) as UserInfoListType
  
  return (
    <Root className={className}>
      <Container>
        <UserContainer>
          <UserIcon src={Avatar} alt={'유저 아바타 이미지'} />
        </UserContainer>
        <UserInfo>
          <UserNicknameTypo>{UserListData[0].nickname}</UserNicknameTypo>
          <DevelopmentStackTagContainer>
            {UserListData[0].developmentStackList.map((UserListItem, index) => (
              <DevelopmentStackTag
                color={getDevelopmentStackColor(UserListItem.developmentStack)}
                key={`development_stack_tag_${index}`}
              >
                {translateDevelopmentStack(UserListItem.developmentStack)}
              </DevelopmentStackTag>
            ))}
          </DevelopmentStackTagContainer>
          <UserIntroductionTypo>{UserListData[0].introduce}</UserIntroductionTypo>
        </UserInfo>
      </Container>
    </Root>
  )
}
