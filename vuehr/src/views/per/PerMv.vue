<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <el-button icon="el-icon-plus" type="primary" @click="show">调动员工</el-button>
            
        </div>
        <div style="margin-top:5px">
            <el-table :data="removes" border stripe size="mini">
                <el-table-column type="selection" align="left" width="55"></el-table-column>
                <el-table-column prop="employee.name" label="姓名" fixed width="90" align="left"></el-table-column>
                <el-table-column prop="employee.workID" label="工号" width="120" align="left"></el-table-column>
                <el-table-column prop="beforedepname" label="调前部门" width="120" align="left"></el-table-column>
                <el-table-column prop="afterdepname" label="调后部门" width="120" align="left"></el-table-column>
                <el-table-column prop="beforejobname" label="调前职位" width="120" align="left"></el-table-column>
                <el-table-column prop="afterjobname" label="调后职位" width="120" align="left"></el-table-column>
                <el-table-column prop="removedate" label="调动日期" width="120" align="left"></el-table-column>
                <el-table-column prop="reason" label="调动原因" width="170" align="left"></el-table-column>
                <el-table-column prop="remark" label="备注" width="170" align="left"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                       
                        <el-button type="danger" @click="deleteRemove(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
                
            </el-table>
            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        background
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total">
                </el-pagination>
            </div>
        </div>
        <el-dialog
                :title="title"
                :visible.sync="dialogVisible"
                width="60%">
            <div>
                <el-form :model="rem" >
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="编号:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="rem.id"
                                placeholder="请输入查询编号" @input="find"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="姓名:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="rem.name"
                                readonly="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="工号:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="rem.workID"
                                readonly="true"></el-input>
                            </el-form-item>
                        </el-col>
                    
                    </el-row>
                    <el-row>
                        
                        <el-col :span="5">
                            <el-form-item label="原部门:" prop="nativePlace">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="rem.department.name" readonly="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="新部门:" prop="departmentId">
                                <el-popover
                                        placement="right"
                                        title="请选择部门"
                                        width="200"
                                        trigger="manual"
                                        v-model="popVisible">
                                    <el-tree default-expand-all :data="allDeps" :props="defaultProps" :expand-on-click-node="false"
                                             @node-click="handleNodeClick"></el-tree>
                                    <div slot="reference"
                                         style="width: 150px;display: inline-flex;font-size: 13px;border: 1px solid #dedede;height: 26px;border-radius: 5px;cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box"
                                         @click="showDepView">{{inputDepName}}
                                    </div>
                                </el-popover>
                            </el-form-item>
                        </el-col>
                        
                        <el-col :span="6">
                            <el-form-item label="原职位:" prop="email">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                          v-model="rem.position.name" readonly="true"></el-input>
                            </el-form-item>
                        </el-col>
                        
                        <el-col :span="6">
                            <el-form-item label="新职位:" prop="posId">
                                <el-select v-model="rem.afterjobid" placeholder="职位" size="mini" style="width: 150px;">
                                    <el-option
                                            v-for="item in positions"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="10">
                            <el-form-item label="调动原因:" prop="name">
                                <el-input size="small"  style="width: 250px" prefix-icon="el-icon-edit" v-model="rem.reason"
                                           type="textarea" :rows="2" placeholder="请输入调动原因" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="备注:" prop="name">
                                <el-input size="small" style="width: 400px" prefix-icon="el-icon-edit" v-model="rem.remark"
                                type="textarea" :rows="2" placeholder="备注"></el-input>
                            </el-form-item>
                        </el-col>
                        
                        
                    </el-row>
                    
                
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddRemove">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PerMv",
        data() {
            return {
                removes: [],
                total: 0,
                currentPage: 1,
                currentSize: 10,
                currentRemove: null,
                title: '',
                dialogVisible: false,
                rem:{
                    department:{},
                    position:{}
                },
                positions:[],
                allDeps:[],
                popVisible: false,
                inputDepName: '所属部门',
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
            }
        },
        mounted() {
          this.initRemoves();
          this.initData();
          this.initPositions();
        },
        methods: {

            sizeChange(size) {
                this.currentSize = size;
                this.initRemoves();
            },
            currentChange(page) {
                this.currentPage = page;
                this.initRemoves();
            },
            initRemoves() {
                this.getRequest("/employee/remove/?page=" + this.currentPage + '&size=' + this.currentSize).then(resp => {
                    if (resp) {
                        this.removes = resp.data;
                    
                        console.log(resp);
                        this.total = resp.total;
                    }
                })
            },
            deleteRemove(data) {
                this.$confirm('此操作将删除【' + data.name + '】调动记录，是否继续？', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定'
                }).then(() => {
                    this.deleteRequest("/employee/remove/" + data.id).then(resp => {
                        if (resp) {
                            this.initRemoves();
                        }
                    })
                }).catch(() => {
                    this.$message.info("取消删除!");
                })
            },
            show() {
                this.title = "调用员工";
                this.dialogVisible = true;
            },

            initPositions() {
                this.getRequest('/employee/basic/positions').then(resp => {
                    if (resp) {
                        this.positions = resp;
                    }
                })
            },
            handleNodeClick(data) {
                this.inputDepName = data.name;
                this.rem.afterdepid = data.id;
            
                this.popVisible = !this.popVisible
            },
            showDepView() {
                this.popVisible = !this.popVisible
            },
            initData() {
                
                
                if (!window.sessionStorage.getItem("deps")) {
                    this.getRequest('/employee/basic/deps').then(resp => {
                        if (resp) {
                            this.allDeps = resp;
                            window.sessionStorage.setItem("deps", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"));
                }
            },
            find() {
                if(this.rem.id) {
                this.getRequest("/employee/basic/getEmp/" + this.rem.id).then(resp => {
                    if(resp) {
                        console.log(resp);
                        this.rem = resp;

                        this.rem.eid = this.rem.id;
                        this.rem.beforedepid = resp.department.id;
                        this.rem.beforejobid = resp.position.id;
                    }

                })
            }
            },
            doAddRemove() {
                this.postRequest("/employee/remove/", this.rem).then(resp => {
                                if (resp) {
                                    console.log(this.rem);
                                    this.dialogVisible = false;
                                    this.initRemoves();
                                }
                            })
            }
        },
    }
</script>

<style scoped>

</style>