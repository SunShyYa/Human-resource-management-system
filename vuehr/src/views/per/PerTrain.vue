<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <el-button icon="el-icon-plus" type="primary" @click="show">添加员工培训</el-button>
            
        </div>
        <div style="margin-top:5px">
            <el-table :data="trains" border stripe size="mini">
                <el-table-column type="selection" align="left" width="55"></el-table-column>
                <el-table-column prop="employee.name" label="姓名" fixed width="120" align="left"></el-table-column>
                <el-table-column prop="employee.workID" label="工号" width="150" align="left"></el-table-column>
                <el-table-column prop="traindate" label="培训日期" width="200" align="left"></el-table-column>
                <el-table-column prop="traincontent" label="培训内容" width="250" align="left"></el-table-column>
                <el-table-column prop="remark" label="备注" width="300" align="left"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditTrainView(scope.row)">编辑</el-button>
                        <el-button type="danger" @click="deleteTrain(scope.row)">删除</el-button>
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
                width="75%">
            <div>
                <el-form :model="tra"  ref="train">
                    <el-row>
                        <el-col :span="5">
                            <el-form-item label="编号:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="tra.eid"
                                          placeholder="请输入员工编号"></el-input>
                            </el-form-item>
                        </el-col>
                        
                        
                        <el-col :span="5">
                            <el-form-item label="培训日期:" prop="birthday">
                                <el-date-picker
                                        v-model="tra.traindate"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="出生日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="培训内容:" prop="name">
                                <el-input size="small"  style="width: 250px" prefix-icon="el-icon-edit" v-model="tra.traincontent"
                                           type="textarea" :rows="2" placeholder="请输入培训内容" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="备注:" prop="name">
                                <el-input size="mini" style="width: 350px" prefix-icon="el-icon-edit" v-model="tra.remark"
                                type="textarea" :rows="2" placeholder="备注"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doAddTrain">确 定</el-button>
             </span>
        </el-dialog>
    </div>
</template>

<script>

    export default {
        name: "PerTrain",
        data() {
            return {
                trains: [],
                total: 0,
                currentPage: 1,
                currentSize: 10,
                currentSalary: null,
                salaries: [],
                title: '',
                dialogVisible: false,
                tra:{}
            }
        },
        mounted() {
            this.initTrains();
            // this.initSalaries();
        },
        methods: {
            sizeChange(size) {
                this.currentSize = size;
                this.initTrains();
            },
            currentChange(page) {
                this.currentPage = page;
                this.initTrains();
            },
            initTrains() {
                this.getRequest("/employee/train/?page=" + this.currentPage + '&size=' + this.currentSize).then(resp => {
                    if (resp) {
                        this.trains = resp.data;
                        console.log(this.trains);
                        
                        this.total = resp.total;
                    }
                })
            },
            deleteTrain(data) {
                this.$confirm('此操作将删除【' + data.name + '】培训，是否继续？', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定'
                }).then(() => {
                    this.deleteRequest("/employee/train/" + data.id).then(resp => {
                        if (resp) {
                            this.initTrains();
                        }
                    })
                }).catch(() => {
                    this.$message.info("取消删除!");
                })
            },
            show() {
                this.emptyTrain();
                this.title = "添加培训";
                this.dialogVisible = true;

            },
            emptyTrain() {
                this.tra = {
                    id: "",
                    name: "",
                    trainDate: "",
                    trainContent: "",
                    remark: ""
                }
            },
            doAddTrain() {
                if (this.tra.id) {
                    console.log(this.tra);
                    
                            this.putRequest("/employee/train/", this.tra).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initTrains();
                                }
                            })
                        }
                        else {
                            this.postRequest("/employee/train/", this.tra).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initTrains();
                                }
                            })
                        }
                    
                    
                },
                showEditTrainView(data) {
                    this.title = "编辑培训信息";
                    this.tra = data;
                    this.dialogVisible = true;
                }

                
            },
        }
    
</script>

<style scoped>

</style>