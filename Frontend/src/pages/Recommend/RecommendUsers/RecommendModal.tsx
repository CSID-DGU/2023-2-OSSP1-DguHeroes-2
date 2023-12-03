import React, { useState, useEffect } from 'react';
import { Modal, Button, Select, Form } from 'antd';
import { stacks } from 'types/stacks'; // 전체 기술 스택

const { Option } = Select;

interface RecommendModalProps {
  visible: boolean;
  selectedItems: string[];
  onCancel: () => void;
  // eslint-disable-next-line no-unused-vars
  onHandlePositionStack: (selectedValues: string[]) => void;
}

const [selectedTarget] = useState<string[]>([]);
const RecommendModal: React.FC<RecommendModalProps> = ({
                                                         visible,
                                                         selectedItems,
                                                         onCancel,
                                                         onHandlePositionStack,
                                                       }) => {
  const [, setRecommendStacks] = useState<string[]>([]);

  return (
    <Modal
      title={`추천 팀원 보기`}
      visible={visible}
      onCancel={onCancel}
      footer={[
        <Button key="close" onClick={onCancel}>
          닫기
        </Button>,
      ]}
    >
      <Form.Item
        style={{ display: 'inline-block', width: 'calc(45% - 8px)', marginLeft: '5px', marginBottom: 0 }}
      >
        <Select mode="multiple" placeholder="추천 팀원" onChange={onHandlePositionStack} value={selectedTarget}>
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
    </Modal>
  );
};

export default RecommendModal;
