import Avatar from 'assets/images/missing_avatar.png'
import { FC, useState } from 'react'
import userListSampleJson from 'constants/json/user_list_sample.json';
import { ButtonWrapper, Root, StackTag, UserIcon, UserNameTypo } from './styled'
import { Button, List, Space } from 'antd'
import { camelizeKey } from 'utils/camelizeKey'
import { UserInfoListType } from 'types/project';

type ApproveMemberSectionProps = {
  className?: string
}

const userListData = camelizeKey(userListSampleJson.user_list) as UserInfoListType;

// 여기에 들어갈 json 데이터 정의 필요!
export const ApproveMemberSection: FC<ApproveMemberSectionProps> = ({ className }) => {
  const filteredUserListData = userListData.filter(
    (userItem) => 
      userItem.userId++ &&
      userItem.nickname.toLowerCase() &&
      userItem.developmentStackList
  )

  const [filteredUserList, setFilteredUserList] = useState<UserInfoListType>(filteredUserListData)
  
  const onClickApproveButton = (userId: number) => {
    setFilteredUserList(filteredUserList.filter((item: any) => item.userId !== userId ))
    // eslint-disable-next-line no-undef
    alert("승인되었습니다")
  }

  const onClickRejectButton = (userId: number) => {
    setFilteredUserList(filteredUserList.filter((item: any) => item.userId !== userId ))
    // eslint-disable-next-line no-undef
    alert("거절되었습니다")
  }

  return (
    <Root className={className}>
      <List
        dataSource={filteredUserList}
        bordered
        renderItem={(item) => (
          <List.Item
            key={item.userId}
            actions={[
              <ButtonWrapper key={`a-${item.userId}`}>
                <Button type="primary" onClick={() => onClickApproveButton(item.userId)}>승인</Button>
                <Button onClick={() => onClickRejectButton(item.userId)}>거절</Button>
              </ButtonWrapper>,
            ]}
          >
            <List.Item.Meta
              avatar={<UserIcon src={Avatar} alt={'프로필 이미지'} />}
              title={<UserNameTypo>{item.nickname}</UserNameTypo>}
              description={
                <Space size={[0, 'small']} wrap>
                  {item.developmentStackList
                    .map((stack) => (
                      <StackTag key={stack.stackId}>{stack.developmentStack}</StackTag>
                    ))
                  }
                </Space>
              }
            />
          </List.Item>
        )}
      />
    </Root>
  )
}
