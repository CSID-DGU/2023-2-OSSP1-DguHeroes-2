/* eslint-disable no-console */
import Avatar from 'assets/images/missing_avatar.png';
import UserListSampleJson from 'constants/json/user_list_sample.json';
import { FC, useEffect, useState } from 'react';
import { UserInfoListType } from 'types/project';
import { camelizeKey } from 'utils/camelizeKey';
import { translateDevelopmentStack, getDevelopmentStackColor } from 'utils/translateDevelopmentStack';
import { PostUpdateUrsResponseType, postUpdateUrs } from 'api/postUpdateUrs';
import { PostUserLogoutResponseType, postuserLogout} from 'api/postUserLogout';
import {
  Root,
  Container,
  UserNicknameTypo,
  UserIcon,
  UserInfo,
  DevelopmentStackTagContainer,
  DevelopmentStackTag,
  UserIntroductionTypo,
  UserContainer,
  UrsContainer,
  CustomButton,
  ScoreDisplay,
} from './styled';
// import { GetUserInfoResponseType, getUserInfo } from 'api/getUserInfo';
// import { identity } from 'lodash';

type ProfileHeaderProps = {
  className?: string;
};

export const ProfileHeader: FC<ProfileHeaderProps> = ({ className }) => {
  const [score, setScore] = useState<number>(0);
  const [updatingScore, setUpdatingScore] = useState<boolean>(false);
  const [id, setId] = useState<string>("");
  useEffect(() => {
    const userId = localStorage.getItem('id') || 'fail'; 
    setId(userId);

    // getUserInfo()
    //   .then((response: GetUserInfoResponseType) => {
    //     if (response.status === 'SUCCESS') {
    //       console.log('SUCCESS');
    //       setScore(response.score);
    //     } else {
    //       console.log('FAIL');
    //       console.log('Error message:', response.message);
    //     }
    //   })
    //   .catch((error: any) => {
    //     console.error('Error:', error);
    //   });
  }, []);

  const UserListData: UserInfoListType = camelizeKey(UserListSampleJson.user_list) as UserInfoListType;

  const updateUrsAPI = () => {
    const data = {
      id: id
    }
    postUpdateUrs(`/user/login`, data)
    .then((response: PostUpdateUrsResponseType) => {
      if (response.status === 'SUCCESS') {
        // eslint-disable-next-line no-undef
        console.log('SUCCESS');
        localStorage.removeItem('test_login')
      } else {
        // eslint-disable-next-line no-undef
        alert("점수 갱신에 실패했습니다.")
        // eslint-disable-next-line no-undef
        console.log('Error message:', response.message);
      }
    })
    .catch((error: any) => {
      // eslint-disable-next-line no-undef
      console.error('Error :', error);
    });
  }

  const handleButtonClick = () => {
    // 버튼 클릭 시 수행할 동작 정의
    console.log('Button Clicked!');
    
    // 1. '갱신 중입니다' 텍스트 표시
    setUpdatingScore(true);

    // 2. 점수 다시 갱신해주는 프로세스 실행 (예시: 1초 후에 갱신 완료로 가정)
    setScore((prevScore) => prevScore + 1);     

    updateUrsAPI()

    setTimeout(() => {   
      // 3. '갱신 중입니다' 텍스트 감추기
      setUpdatingScore(false);
    }, 600000);
  };

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

          {/* 버튼과 점수 표시 추가 */}
          <UrsContainer>
            <CustomButton onClick={handleButtonClick}>점수 갱신</CustomButton>
            <ScoreDisplay>Score: {id}</ScoreDisplay>

            {/* '갱신 중입니다' 텍스트 팝업 */}
            {updatingScore && <div> 갱신 중입니다! GitHub repository의 양에 따라 소요 시간은 상이합니다. </div>}
          </UrsContainer>
        </UserInfo>
      </Container>
    </Root>
  )
}

