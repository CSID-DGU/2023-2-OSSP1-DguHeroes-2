/* eslint-disable no-console */
import React, { useState, useEffect } from 'react';
import { Modal, Select, Form, Button, List, message } from 'antd';
import { stacks } from 'types/stacks';
import { testdata } from 'types/testdata';

const { Option } = Select;

interface RecommendModalProps {
  visible: boolean;
  selectedItems: string[];
  onCancel: () => void;
  onHandlePositionStack: (selectedValues: string[]) => void;
}

const RecommendModal: React.FC<RecommendModalProps> = ({
                                                         visible,
                                                         selectedItems,
                                                         onCancel,
                                                         onHandlePositionStack,
                                                       }) => {
  const [selectedTargets, setSelectedTargets] = useState<string[]>([]);
  const [recommendedUsers, setRecommendedUsers] = useState<
    { user_id: number; user_stacks: string[] }[]
  >([]);

  // State to track whether the invitation has been sent
  const [invitationSent, setInvitationSent] = useState<{ [key: number]: boolean }>({});

  const handleSelectChange = (values: string[]) => {
    setSelectedTargets(values);
  };

  const handleCancel = () => {
    onCancel();
    setSelectedTargets([]);
  };

  const fetchRecommendedUsers = async (selectedStacks: string[]) => {
    try {
      const foundUsers = testdata.filter((user) =>
        selectedStacks.every((stack) => user.user_stacks.includes(stack))
      );

      const userInformation = foundUsers.map(({ user_id, user_stacks }) => ({
        user_id,
        user_stacks,
      }));

      console.log('User Information:', userInformation);

      setRecommendedUsers(userInformation);
    } catch (error) {
      console.error('Error fetching recommended users:', error);
    }
  };

  const inviteUser = (userId: number) => {
    // Replace this with your actual invitation logic
    // For demonstration purposes, it shows a success message
    message.success(`Invitation sent to User ID ${userId}`);

    // Update the state to mark the invitation as sent
    setInvitationSent((prev) => ({ ...prev, [userId]: true }));
  };

  useEffect(() => {
    if (selectedTargets.length > 0) {
      fetchRecommendedUsers(selectedTargets);
    } else {
      setRecommendedUsers([]);
    }
  }, [selectedTargets]);

  return (
    <Modal
      title="추천 팀원 보기"
      visible={visible}
      onCancel={handleCancel}
      width={1500} // Adjust the width as needed
      bodyStyle={{ height: '800px', overflowY: 'auto' }}
      footer={null} // Remove the footer to hide the OK and Cancel buttons
    >
      <Form.Item>
        <Select
          mode="multiple"
          placeholder="추천 팀원"
          onChange={handleSelectChange}
          value={selectedTargets}
          style={{ width: '100%' }}
        >
          {selectedItems.map((selectItem) => {
            const stackItem = stacks.find((stack) => stack.value === selectItem);
            return (
              <Option key={stackItem?.value} value={stackItem?.label}>
                {stackItem?.label}
              </Option>
            );
          })}
        </Select>
      </Form.Item>
      {selectedTargets.length > 0 && (
        <List
          header={<div>추천된 유저 목록</div>}
          bordered
          dataSource={recommendedUsers}
          renderItem={(user) => (
            <List.Item>
              <div>User ID: {user.user_id}</div>
              <div>
                User Stacks:{' '}
                {user.user_stacks.map((stack, index) => (
                  <span
                    key={index}
                    style={{
                      color: selectedTargets.includes(stack) ? 'red' : 'inherit',
                    }}
                  >
                    {stack}
                    {index < user.user_stacks.length - 1 && ', '}
                  </span>
                ))}
              </div>
              <Button
                type="primary"
                onClick={() => inviteUser(user.user_id)}
                disabled={invitationSent[user.user_id]}
                style={{ backgroundColor: invitationSent[user.user_id] ? 'gray' : undefined }}
                //버튼 누르면 버튼 비활성화 but 페이지 새로고침하면 버튼 다시 활성화되므로 데이터베이스를 통해 초대를 보낸 적이 있는지 없는지,
                //보냈다면 상대가 수락했는지 거절했는지, 거절 시점이 언제인지를 고려하여 페이지 새로고침 해도 다시 버튼 활성화되지 않도록 하는 프로세스 필요
              >
                초대
              </Button>
            </List.Item>
          )}
        />
      )}
    </Modal>
  );
};

export default RecommendModal;


