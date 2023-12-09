/* eslint-disable no-console */
import Avatar from 'assets/images/missing_avatar.png';
import UserListSampleJson from 'constants/json/user_list_sample.json';
import { FC, useEffect, useState } from 'react';
import { UserInfoListType } from 'types/project';
import { camelizeKey } from 'utils/camelizeKey';
import { translateDevelopmentStack, getDevelopmentStackColor } from 'utils/translateDevelopmentStack';
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
import { GetUserInfoResponseType, getUserInfo } from 'api/getUserInfo';

type ProfileHeaderProps = {
  className?: string;
};

export const ProfileHeader: FC<ProfileHeaderProps> = ({ className }) => {
  const [score, setScore] = useState<number>(0);

  useEffect(() => {
    getUserInfo()
      .then((response: GetUserInfoResponseType) => {
        if (response.status === 'SUCCESS') {
          // eslint-disable-next-line no-undef
          console.log('SUCCESS');
          // 값 받아오기
          setScore(response.score); // API에서 점수를 받아와서 state 업데이트
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
  }, []);

  const UserListData: UserInfoListType = camelizeKey(UserListSampleJson.user_list) as UserInfoListType;

  const handleButtonClick = () => {
    // 버튼 클릭 시 수행할 동작 정의
    console.log('Button Clicked!');
    // 1. 점수 다시 갱신해주는 프로세스 실행
    // setScore(response.data.score); // 2. API에서 점수를 받아와서 state 업데이트
    setScore((prevScore) => prevScore + 1); // 예시: 버튼 클릭 시 점수 증가
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
            <ScoreDisplay>Score: {score}</ScoreDisplay>
          </UrsContainer>
        </UserInfo>
      </Container>
    </Root>
  );
};
